package org.example;

import org.example.zoomcar.controller.BookingController;
import org.example.zoomcar.controller.BranchController;
import org.example.zoomcar.model.Booking;
import org.example.zoomcar.model.InventoryInfo;
import org.example.zoomcar.model.TimeSlot;
import org.example.zoomcar.model.VehicleType;
import org.example.zoomcar.repository.*;
import org.example.zoomcar.service.BookingService;
import org.example.zoomcar.service.BranchService;
import org.example.zoomcar.strategy.IBookingSelectionStrategy;
import org.example.zoomcar.strategy.PriceBasedBookingSelectionStrategy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        IBranchRepository branchRepository = new InMemoryBranchRepository();
        IBookingRepository bookingRepository = new InMemoryBookingRepository();
        IPricingRepository pricingRepository = new InMemoryPricingRepository();
        BranchService branchService = new BranchService(branchRepository, pricingRepository, bookingRepository);

        IBookingSelectionStrategy bookingSelectionStrategy = new PriceBasedBookingSelectionStrategy(branchService);

        BookingService bookingService = new BookingService(bookingRepository, pricingRepository, bookingSelectionStrategy, branchService);
        BookingController bookingController = new BookingController(bookingService);
        BranchController branchController = new BranchController(branchService);

        /*
        allocatePrice(“Vasanth Vihar”, VehicleType.Sedan, 100)
        allocatePrice(“Vasanth Vihar”, VehicleType.Hatchback, 80)
        allocatePrice(“Cyber City”, VehicleType.Sedan, 200)
        allocatePrice(“Cyber City”, VehicleType.Hatchback, 50)
        addVehicle(“DL 01 MR 9310”, VehicleType.Sedan, “Vasanth Vihar”)
        addVehicle(“DL 01 MR 9311”, VehicleType.Sedan, “Cyber City”)
        addVehicle(“DL 01 MR 9312”, VehicleType.Hatchback, “Cyber City”)
        bookVehicle(VehicleType.Sedan, 29-02-2020 10:00 AM, 29-02-2020 01:00 PM)
         */

        branchController.addBranch("Vasanth Vihar");
        branchController.addBranch("Cyber City");
        branchController.allocatePrice("Vasanth Vihar", VehicleType.SEDAN, 100);
        branchController.allocatePrice("Vasanth Vihar", VehicleType.HATCHBACK, 80);
        branchController.allocatePrice("Cyber City", VehicleType.SEDAN, 200);
        branchController.allocatePrice("Cyber City", VehicleType.HATCHBACK, 50);
        branchController.addVehicle("DL 01 MR 9310", VehicleType.SEDAN, "Vasanth Vihar");
        branchController.addVehicle("DL 01 MR 9311", VehicleType.SEDAN, "Cyber City");
        branchController.addVehicle("DL 01 MR 9312", VehicleType.HATCHBACK, "Cyber City");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a", Locale.ENGLISH);


        Booking booking1 = bookingController.bookVehicle(VehicleType.SEDAN, LocalDateTime.parse("06-08-2025 10:00 AM", formatter), LocalDateTime.parse("06-08-2025 01:00 PM", formatter));
        System.out.println(booking1.toString());
        Booking booking2 = bookingController.bookVehicle(VehicleType.SEDAN, LocalDateTime.parse("06-08-2025 02:00 PM", formatter), LocalDateTime.parse("06-08-2025 03:00 PM", formatter));
        System.out.println(booking2);
        Booking booking3 = bookingController.bookVehicle(VehicleType.SEDAN, LocalDateTime.parse("06-08-2025 02:00 PM", formatter), LocalDateTime.parse("06-08-2025 03:00 PM", formatter));
        System.out.println(booking3);
        Booking booking4 = bookingController.bookVehicle(VehicleType.SEDAN, LocalDateTime.parse("06-08-2025 02:00 PM", formatter), LocalDateTime.parse("06-08-2025 03:00 PM", formatter));
        System.out.println(booking4);

        List<InventoryInfo> inventoryInfoList = branchController.getInventoryInfo(new TimeSlot(LocalDateTime.parse("06-08-2025 02:00 PM", formatter), LocalDateTime.parse("06-08-2025 03:00 PM", formatter)));
        System.out.println(inventoryInfoList);
    }
}