class VersionedEntity implements Comparable<VersionedEntity> {
    private String version;

    public VersionedEntity(final String version) {
        this.version = version;
    }

    @Override
    public int compareTo(final VersionedEntity other) {
        String[] s1 = version.split("\\.");
        String[] s2 = other.version.split("\\.");

        int i = 0, j = 0;

        while (i < s1.length || j < s2.length) {
            int v1 = 0, v2 = 0;

            if (i < s1.length) {
                v1 = Integer.parseInt(s1[i++]);
            }

            if (j < s2.length) {
                v2 = Integer.parseInt(s2[j++]);
            }

            if (v1 < v2) {
                return -1;
            } else if (v1 > v2) {
                return 1;
            }
        }
        return 0;
    }

    public String getVersion() {
        return version;
    }
}

public class CompareVersions {
    public int compare(String version1, String version2) {
        VersionedEntity v1 = new VersionedEntity(version1);
        VersionedEntity v2 = new VersionedEntity(version2);

        return v1.compareTo(v2);
    }
}

class Solution {
    public static void main(String[] args) {
        CompareVersions cv = new CompareVersions();
        String v1, v2;
        int result;

        v1 = "1.2"; v2 ="1.10";
        result = cv.compare(v1, v2);
        print(v1, v2, result);

        v1 = "1.01"; v2 ="1.001";
        result = cv.compare(v1, v2);
        print(v1, v2, result);

        v1 = "1.0"; v2 ="1.0.0.0";
        result = cv.compare(v1, v2);
        print(v1, v2, result);

        v1 = "1.0.0.0.0.0"; v2 ="1.0.0.0";
        result = cv.compare(v1, v2);
        print(v1, v2, result);

        v1 = "1.0.0.0.0.0.1"; v2 ="1.0.0.0";
        result = cv.compare(v1, v2);
        print(v1, v2, result);

        v1 = "1.0.0.0"; v2 ="1.0.0.0.0.0.0.1";
        result = cv.compare(v1, v2);
        print(v1, v2, result);
    }

    public static void print(String v1, String v2, int result) {
        System.out.println("v1: " + v1 + " v2: " + v2 + " result = " + result);
    }
}
