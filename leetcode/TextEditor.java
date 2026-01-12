package org.example;

import java.util.Stack;

abstract class Operation {}
class InsertAtEndOperation extends Operation {
    String charsToInsert;
    InsertAtEndOperation(String charsToInsert) {
        this.charsToInsert = charsToInsert;
    }
}
class DeleteFromEndOperation extends Operation {
    int numCharsToDelete;
    DeleteFromEndOperation(int numCharsToDelete) {
        this.numCharsToDelete = numCharsToDelete;
    }
}
class TextDocument {

    private StringBuffer buffer;
    private Stack<Operation> undoStack;
    private Stack<Operation> redoStack;

    TextDocument() {
        buffer = new StringBuffer();
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    private Operation executeOperation(Operation operation) {
        Operation inverseOperation = getInverseOperation(operation);
        if (operation instanceof InsertAtEndOperation ie) {
            String charsToInsert = ie.charsToInsert;
            buffer.append(charsToInsert);
        } else {
            DeleteFromEndOperation de = (DeleteFromEndOperation)operation;
            int size = de.numCharsToDelete;
            buffer.setLength(buffer.length() - size);
        }
        return inverseOperation;
    }

    private Operation getInverseOperation(Operation op) {
        if (op instanceof InsertAtEndOperation ie){
            return new DeleteFromEndOperation(ie.charsToInsert.length());
        } else {
            DeleteFromEndOperation de = (DeleteFromEndOperation)op;
            return new InsertAtEndOperation(buffer.substring(buffer.length() - de.numCharsToDelete));
        }
    }

    public void applyOperation(Operation op) {
        redoStack.clear();
        Operation inverseOp = executeOperation(op);
        undoStack.push(inverseOp);
    }

    public void undoLast() {
        if (!undoStack.isEmpty()) {
            Operation op = undoStack.pop();
            Operation inverseOp = getInverseOperation(op);
            redoStack.push(inverseOp);
            executeOperation(op);
        }
    }

    public void redoLast() {
        if (!redoStack.isEmpty()) {
            Operation op = redoStack.pop();
            Operation inverseOperation = getInverseOperation(op);
            undoStack.push(inverseOperation);
            executeOperation(op);
        }
    }

    public String getCurrentContent() {
        return buffer.toString();
    }
}
// Main class should be named 'Solution' and should not be public.
public class TextEditor {

    public static void main(String[] args) {
        testUndoRedoBasic();
        testBasicOperations();
        testMixedOperations();
        testConsecutiveUndos();
        testUndoRedoOnEmpty();
        testInterleavedUndoRedo();
    }

    private static void assertEquals(String actual, String expected) {
        assert expected.equals(actual) : "Values do not match: expected " + expected + " but got " + actual;
        System.out.println("Assertion passed, expected " + expected + " and actual is " + actual);
    }


    private static void testUndoRedoBasic() {
        TextDocument doc = new TextDocument();

        // Insert "hello"
        doc.applyOperation(new InsertAtEndOperation("hello"));
        assertEquals(doc.getCurrentContent(), "hello");

        // Insert "world"
        doc.applyOperation(new InsertAtEndOperation("world"));
        assertEquals(doc.getCurrentContent(), "helloworld");

        // Undo last operation (removes "world")
        doc.undoLast();
        assertEquals(doc.getCurrentContent(), "hello");

        // Redo last operation (re-inserts "world")
        doc.redoLast();
        assertEquals(doc.getCurrentContent(), "helloworld");
    }


    private static void testBasicOperations() {
        TextDocument doc = new TextDocument();
        assertEquals(doc.getCurrentContent(), "");

        doc.applyOperation(new InsertAtEndOperation("hello"));
        assertEquals(doc.getCurrentContent(), "hello");

        doc.applyOperation(new InsertAtEndOperation("world"));
        assertEquals(doc.getCurrentContent(), "helloworld");

        doc.applyOperation(new DeleteFromEndOperation(5));
        assertEquals(doc.getCurrentContent(), "hello");

        doc.undoLast();
        assertEquals(doc.getCurrentContent(), "helloworld");

        doc.redoLast();
        assertEquals(doc.getCurrentContent(), "hello");

        doc.undoLast();
        assertEquals(doc.getCurrentContent(), "helloworld");

        doc.undoLast();
        assertEquals(doc.getCurrentContent(), "hello");
    }

    // Test Case 2: Multiple Insertions and Mixed Undo/Redo
    private static void testMixedOperations() {
        TextDocument doc = new TextDocument();
        doc.applyOperation(new InsertAtEndOperation("Hello"));
        assertEquals(doc.getCurrentContent(), "Hello");

        doc.applyOperation(new InsertAtEndOperation(", "));
        assertEquals(doc.getCurrentContent(), "Hello, ");

        doc.applyOperation(new InsertAtEndOperation("World"));
        assertEquals(doc.getCurrentContent(), "Hello, World");

        doc.applyOperation(new DeleteFromEndOperation(5));
        assertEquals(doc.getCurrentContent(), "Hello, ");

        doc.undoLast(); // undo deletion
        assertEquals(doc.getCurrentContent(), "Hello, World");

        doc.undoLast(); // undo "World" insertion
        assertEquals(doc.getCurrentContent(), "Hello, ");

        doc.redoLast(); // redo "World" insertion
        assertEquals(doc.getCurrentContent(), "Hello, World");
    }

    // Test Case 3: Consecutive Undos
    private static void testConsecutiveUndos() {
        TextDocument doc = new TextDocument();
        doc.applyOperation(new InsertAtEndOperation("a"));
        doc.applyOperation(new InsertAtEndOperation("b"));
        doc.applyOperation(new InsertAtEndOperation("c"));
        assertEquals(doc.getCurrentContent(), "abc");

        doc.undoLast(); // removes "c"
        assertEquals(doc.getCurrentContent(), "ab");

        doc.undoLast(); // removes "b"
        assertEquals(doc.getCurrentContent(), "a");

        doc.undoLast(); // removes "a"
        assertEquals(doc.getCurrentContent(), "");
    }

    // Test Case 4: Undo/Redo on an Empty Document
    private static void testUndoRedoOnEmpty() {
        TextDocument doc = new TextDocument();
        doc.undoLast();
        assertEquals(doc.getCurrentContent(), "");

        doc.redoLast();
        assertEquals(doc.getCurrentContent(), "");
    }

    // Test Case 5: Interleaving Undo and Redo
    private static void testInterleavedUndoRedo() {
        TextDocument doc = new TextDocument();
        doc.applyOperation(new InsertAtEndOperation("first"));
        assertEquals(doc.getCurrentContent(), "first");

        doc.applyOperation(new InsertAtEndOperation("second"));
        assertEquals(doc.getCurrentContent(), "firstsecond");

        doc.applyOperation(new DeleteFromEndOperation(2));
        // Assuming deletion removes the last 2 characters ("nd")
        assertEquals(doc.getCurrentContent(), "firstseco");

        doc.undoLast(); // undo deletion
        assertEquals(doc.getCurrentContent(), "firstsecond");

        doc.undoLast(); // undo "second" insertion
        assertEquals(doc.getCurrentContent(), "first");

        doc.redoLast(); // redo "second" insertion
        assertEquals(doc.getCurrentContent(), "firstsecond");

        doc.redoLast(); // redo deletion
        assertEquals(doc.getCurrentContent(), "firstseco");
    }
}

