package com1032.cw;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		System.out.println("---------------------------------------------------------------");
		System.out.println("Start Component B.1");
		System.out.println("---------------------------------------------------------------");

		// Create Main Memory
		Memory M = new Memory(1024, 124);
		M.memoryState();

		// Create process examples for tasks B.1
		Process p1 = new Process("1, 100, 200, 20");
		Process p2 = new Process("2, 70, 87, 20, 55");
		Process p3 = new Process("3, 10, 260, 40, 10, 70");
		Process p4 = new Process("4, 150, 80, 30, 40, 20, 60");
		Process p5 = new Process("5, 40, 60, 120, 90, 30, 80, 50");

		// allocate the first segment from P1 into memory
		System.out.println("\nAdd Segment [P1, S0] to Main Memory from Process " + p1.toString());
		System.out.println("\n+ State Before");
		M.memoryState(); // show how the memory looks after the operations
		p1.segmentTable(); // show how the segment table looks after the operations
		System.out.println("- State After");
		M.allocate(p1, p1.getSegment(0));
		M.memoryState(); // show how the memory looks after the operations
		p1.segmentTable(); // show how the segment table looks after the operations

		// Next, allocate all segments from P1 into memory
		// (This would skip over the already allocated [P1, S1]
		// Note that P1.S1 is already in the memory. Each segment can only be added once
		System.out.println("Add all segments of P1 to Main Memory .");
		M.allocate(p1); // or some variant of this form to all Segments of P1 in main memory.
		M.memoryState(); // show how the memory looks after the operations
		p1.segmentTable(); // show how the segment table looks after the operations

		// Resize P1
		System.out.println("Resize process " + p1.toString());
		M.resizeProcess(p1, "-100, 10, 10");
		M.memoryState();
		p1.segmentTable();

		System.out.println("Deallocate segment [P1 S1] from the main memory ");
		M.deallocate(p1, p1.getSegment(1));
		M.memoryState();
		p1.segmentTable();

		System.out.println("Deallocate process " + p1.toString() + " from the memory");
		M.deallocate(p1);
		M.memoryState();
		p1.segmentTable();

		System.out.println("---------------------------------------------------------------");

		System.out.println("Add Segment [P2, S1] to Main Memory from Process " + p1.toString());
		System.out.println("\n+ State Before");
		M.memoryState();
		p2.segmentTable();
		M.allocate(p2, p2.getSegment(1));
		System.out.println("- State After");
		M.memoryState();
		p2.segmentTable();
		System.out.println("Add all segments of P2 to main memory.");
		M.allocate(p2);
		M.memoryState();
		p2.segmentTable();
		System.out.println("Resize process " + p2.toString());
		M.resizeProcess(p2, "20, -87, 30, 10");
		M.memoryState();
		p2.segmentTable();
		System.out.println("Deallocate segment [P2, S2] from the main memory ");
		M.deallocate(p2, p2.getSegment(2));
		M.memoryState();
		p2.segmentTable();
		System.out.println("Deallocate process " + p2.toString() + " from the memory");
		M.deallocate(p2);
		M.memoryState();
		p2.segmentTable();

		System.out.println("---------------------------------------------------------------");

		System.out.println("Add Segment [P3, S1] to Main Memory from Process " + p3.toString());
		System.out.println("\n+ State Before");
		M.memoryState();
		p3.segmentTable();
		M.allocate(p3, p3.getSegment(1));
		System.out.println("- State After");
		M.memoryState();
		p3.segmentTable();
		System.out.println("Add all segments of P3 to main memory.");
		M.allocate(p3);
		M.memoryState();
		p3.segmentTable();
		System.out.println("Resize process " + p3.toString());
		M.resizeProcess(p3, "10, -260, 20, 40, -10, 30");
		M.memoryState();
		p3.segmentTable();
		System.out.println("Deallocate segment [P3, S2] from the main memory ");
		M.deallocate(p3, p3.getSegment(2));
		M.memoryState();
		p3.segmentTable();
		System.out.println("Deallocate process " + p3.toString() + " from the memory");
		M.deallocate(p3);
		M.memoryState();
		p3.segmentTable();

		System.out.println("---------------------------------------------------------------");

		System.out.println("Add Segment [P4, S1] to Main Memory from Process " + p4.toString());
		System.out.println("\n+ State Before");
		M.memoryState();
		p4.segmentTable();
		M.allocate(p4, p4.getSegment(1));
		System.out.println("- State After");
		M.memoryState();
		p4.segmentTable();
		System.out.println("Add all segments of P4 to main memory.");
		M.allocate(p4);
		M.memoryState();
		p4.segmentTable();
		System.out.println("Resize process " + p4.toString());
		M.resizeProcess(p4, "150, -80, 20, 30, 40, -20, 50");
		M.memoryState();
		p4.segmentTable();
		System.out.println("Deallocate segment [P4, S3] from the main memory ");
		M.deallocate(p4, p4.getSegment(3));
		M.memoryState();
		p4.segmentTable();
		System.out.println("Deallocate process " + p4.toString() + " from the memory");
		M.deallocate(p4);
		M.memoryState();
		p4.segmentTable();

		System.out.println("---------------------------------------------------------------");

		System.out.println("Add Segment [P5, S6] to Main Memory from Process " + p5.toString());
		System.out.println("\n+ State Before");
		M.memoryState();
		p5.segmentTable();
		M.allocate(p5, p5.getSegment(6));
		System.out.println("- State After");
		M.memoryState();
		p5.segmentTable();
		System.out.println("Add all segments of P5 to main memory.");
		M.allocate(p5);
		M.memoryState();
		p5.segmentTable();
		System.out.println("Resize process " + p5.toString());
		M.resizeProcess(p5, "40, -60, 80, -90, 30, -80, 40");
		M.memoryState();
		p5.segmentTable();
		System.out.println("Deallocate segment [P5, S4] from the main memory ");
		M.deallocate(p5, p5.getSegment(4));
		M.memoryState();
		p5.segmentTable();
		System.out.println("Deallocate process " + p5.toString() + " from the memory");
		M.deallocate(p5);
		M.memoryState();
		p5.segmentTable();

		System.out.println("---------------------------------------------------------------");
		System.out.println("End Component B.1");
		System.out.println("---------------------------------------------------------------");

		System.out.println("---------------------------------------------------------------");
		System.out.println("\nStart Component B.2.1");
		System.out.println("---------------------------------------------------------------");

		M.memoryState();
		Process p6 = new Process("6, 10, 260, 40, 10, 70");
		p6.segmentTable();
		System.out.println("\nAdd Segment [P6, S0] to Main Memory from Process " + p6.toString());
		M.allocate(p6, p6.getSegment(0));
		M.memoryState();
		p6.segmentTable();
		System.out.println("\nAdd Segment [P6, S4] to Main Memory from Process " + p6.toString());
		M.allocate(p6, p6.getSegment(4));
		M.memoryState();
		p6.segmentTable();
		System.out.println("\nAdd Segment [P6, S1] to Main Memory from Process " + p6.toString());
		M.allocate(p6, p6.getSegment(1));
		M.memoryState();
		p6.segmentTable();
		System.out.println("\nAdd Segment [P6, S3] to Main Memory from Process " + p6.toString());
		M.allocate(p6, p6.getSegment(3));
		M.memoryState();
		p6.segmentTable();
		System.out.println("\nAdd Segment [P6, S2] to Main Memory from Process " + p6.toString());
		M.allocate(p6, p6.getSegment(2));
		M.memoryState();
		p6.segmentTable();

		System.out.println("---------------------------------------------------------------");
		System.out.println("End Component B.2.1");
		System.out.println("---------------------------------------------------------------");

		System.out.println("---------------------------------------------------------------");
		System.out.println("\nStart Component B.2.2");
		System.out.println("---------------------------------------------------------------");

		M = new Memory(1024, 124);
		M.memoryState();
		Process p7 = new Process("7, [70; r--], [87; -w-], [20; r-x], [55; rwx]");
		p7.segmentTable();
		System.out.println("Changing P7 S0 permissions to rwx");
		p7.changeSegmentPermissions(0, "rwx");
		p7.segmentTable();
		System.out.println("Allocating P7 S1, P7 S2");
		M.allocate(p7, p7.getSegment(1));
		M.allocate(p7, p7.getSegment(2));
		M.memoryState();
		p7.segmentTable();
		System.out.println("Finding segment in address 150, it has no read permission.");
		M.findSegmentByAddress(150);
		System.out.println("Finding segment in address 215, it has read permissions.");
		M.findSegmentByAddress(215);
		System.out.println("Finding segment in address 1, there are no segments there.");
		M.findSegmentByAddress(1);

		System.out.println("---------------------------------------------------------------");
		System.out.println("End Component B.2.2");
		System.out.println("---------------------------------------------------------------");

		System.out.println("---------------------------------------------------------------");
		System.out.println("Start Component B.2.3");
		System.out.println("---------------------------------------------------------------");

		M = new Memory(1024, 124);
		M.memoryState();
		Process p10 = new Process("10, 100, 200, 20");
		M.allocate(p10);
		M.memoryState();
		System.out.println("Contents of TLB:");
		M.tlb();
		M.findSegmentByAddress(125);

		System.out.println("---------------------------------------------------------------");
		System.out.println("End Component B.2.3");
		System.out.println("---------------------------------------------------------------");

		System.out.println("---------------------------------------------------------------");
		System.out.println("\nStart Component B.2.4");
		System.out.println("---------------------------------------------------------------");

		M = new Memory(1024, 124);
		M.memoryState();
		Process p8 = new Process("8, 100, 200, 20");
		Process p9 = new Process("9, 70, 87, 20, 55");
		p8.segmentTable();
		p9.segmentTable();
		System.out.println("\n+Before");
		M.allocate(p8);
		M.allocate(p9);
		M.memoryState();
		p1.segmentTable();
		p2.segmentTable();
		System.out.println("Resize process P8");

		System.out.println("\n+After");
		M.memoryState();
		p8.segmentTable();
		p9.segmentTable();

		System.out.println("Deallocate process P8 from the memory");
		M.deallocate(p8);
		System.out.println("\n+After");
		M.memoryState();
		p8.segmentTable();
		p9.segmentTable();

		System.out.println("---------------------------------------------------------------");
		System.out.println("End Component B.2.4");
		System.out.println("---------------------------------------------------------------");

		System.out.println("---------------------------------------------------------------");
		System.out.println("\nStart Component B.3");
		System.out.println("---------------------------------------------------------------");

		System.out.println("Test Invalid memory size");
		try {
			M = new Memory(0, 128);
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
		}
		System.out.println("Test Invalid OS size (0)");
		try {
			M = new Memory(100, 0);
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
		}
		System.out.println("Test Invalid OS size (greater than size)");
		try {
			M = new Memory(100, 128);
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
		}
		System.out.println("Test enough memory for segment allocation");
		try {
			M = new Memory(126, 124);
			Process p11 = new Process("11, 100, 200, 20");
			M.allocate(p11);
			M.allocate(p11, p11.getSegment(0));
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
		}
		System.out.println("Test Invalid segmentId");
		try {
			Segment s = new Segment(-1, 0, 0, "r--");
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
		}
		System.out.println("Test invalid base");
		try {
			Segment s = new Segment(1, -1, 0, "r--");
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
		}
		System.out.println("Test invalid limit");
		try {
			Segment s = new Segment(1, 0, -1, "r--");
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
		}
		System.out.println("Test invalid setLimit");
		try {
			Segment s = new Segment(1, 0, 1, "r--");
			s.setLimit(-1);
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
		}
		System.out.println("Test invalid setBase");
		try {
			Segment s = new Segment(1, 0, 1, "r--");
			s.setBase(-1);
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
		}
		System.out.println("Test invalid setPermissions");
		try {
			Segment s = new Segment(1, 0, 1, "r--");
			s.setPermissions("---");
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
		}
		System.out.println("Test Invalid PID in TLBManager");
		try {
			TLBManager tlbManager = new TLBManager();
			tlbManager.addEntry("", "1", 1);
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
		}
		System.out.println("Test Invalid SID in TLBManager");
		try {
			TLBManager tlbManager = new TLBManager();
			tlbManager.addEntry("1", "", 1);
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
		}
		System.out.println("Test Invalid Segment List in SegmentTable");
		try {
			ArrayList<Segment> segments = null;
			SegmentTable segmentTable = new SegmentTable(segments);
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
		}
		System.out.println("Test Invalid Segment addition in SegmentTable");
		try {
			SegmentTable segmentTable = new SegmentTable(new ArrayList<>());
			segmentTable.addSegment(null);
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
		}
		System.out.println("Test Addition of Existing Segment in SegmentTable");
		try {
			SegmentTable segmentTable = new SegmentTable(new ArrayList<>());
			Segment segment = new Segment(1, 0, 1, "r--");
			segmentTable.addSegment(segment);
			segmentTable.addSegment(segment);
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
		}
		System.out.println("Test Invalid Segment Removal in SegmentTable");
		try {
			SegmentTable segmentTable = new SegmentTable(new ArrayList<>());
			segmentTable.removeSegment(null);
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
		}
		System.out.println("Test Removal of Non-Existing Segment in SegmentTable");
		try {
			SegmentTable segmentTable = new SegmentTable(new ArrayList<>());
			Segment segment = new Segment(1, 0, 1, "r--");
			segmentTable.removeSegment(segment);
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
		}
		System.out.println("Test Invalid Process String");
		try {
			Process process = new Process("");
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
		}

		System.out.println("---------------------------------------------------------------");
		System.out.println("End Component B.3");
		System.out.println("---------------------------------------------------------------");
	}
}
