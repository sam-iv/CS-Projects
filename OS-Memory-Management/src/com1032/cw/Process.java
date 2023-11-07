package com1032.cw;

import java.util.ArrayList;

/**
 * A class to model a process.
 * 
 * @author Samuel Ivuerah
 */
public class Process {

	/** A counter to ensure unique process IDs. */
	private static int pidCounter = 0;

	/** The process ID. */
	private int pid = 0;

	/** The segment table belonging to the process. */
	private SegmentTable segmentTable;

	/**
	 * A constructor to instantiate a new Process with a unique ID, and segments.
	 * 
	 * @param processString The input string for the process to make segments, with
	 *                      permissions.
	 * @throws IllegalArgumentException If the process string is invalid.
	 */
	public Process(String processString) throws IllegalArgumentException {
		if (processString.isEmpty() || processString.isBlank()) {
			throw new IllegalArgumentException("process string is invalid");
		}
		pid = ++pidCounter; // Increment the process ID counter and assign it to the process ID.
		ArrayList<Segment> segmentList = new ArrayList<Segment>();

		Parser parser = new Parser(); // Using the parser class to parse, sort, the process string.
		ArrayList<String>[] list = parser.parseInputString(processString);

		for (int id = 1; id < list.length; id++) { // Iterate through segments and instantiate those segments.
			int segmentSize = Integer.valueOf(list[id].get(0));
			int segmentBase = 0;
			int segmentLimit = segmentSize;

			String permissions = "rwx"; // Default permission.
			if (list[id].size() > 1) {
				permissions = list[id].get(1); // If there is a specified permission, use that.
			}

			Segment segment = new Segment(id - 1, segmentBase, segmentLimit, permissions);
			segmentList.add(segment); // Add segment to the list
		}
		segmentTable = new SegmentTable(segmentList); // Create a segment table, with the list of segments, for this
														// process.
	}

	public int getPid() {
		return pid;
	}

	public SegmentTable getSegmentTable() {
		return segmentTable;
	}

	/**
	 * A method to change a segment's permission by using
	 * {@link com1032.cw.Segment#setPermissions(String)}.
	 * 
	 * @param segmentId      The SID of the segment in question.
	 * @param newPermissions The new permissions to set.
	 * @throws IllegalArgumentException If the new permissions are invalid.
	 */
	public void changeSegmentPermissions(int segmentId, String newPermissions) throws IllegalArgumentException {
		if (newPermissions.isEmpty() || newPermissions.isBlank()) {
			throw new IllegalArgumentException("process string is invalid");
		}
		Segment segment = getSegment(segmentId);
		if (segment != null) {
			segment.setPermissions(newPermissions);
		} else {
			System.out.println("Error: No segment found with ID " + segmentId + ".");
		}
	}

	/**
	 * A method to resize a process' segments.
	 * 
	 * @param segments The new segments' limits.
	 * @return A list of the new segments.
	 * @throws IllegalArgumentException If the segments are invalid.
	 */
	public ArrayList<Segment> resize(String segments) throws IllegalArgumentException {
		if (segments == null) {
			throw new IllegalArgumentException("Segments is invalid.");
		}
		Parser parser = new Parser();
		ArrayList<String>[] list = parser.parseInputString(segments);

		ArrayList<Segment> segmentsToRemove = new ArrayList<>();

		for (int id = 0; id < list.length; id++) { // Iterate through segments.
			int deltaSegmentSize = Integer.valueOf(list[id].get(0));
			Segment segment = getSegment(id);
			if (segment != null) {
				int newSegmentSize = segment.getLimit() + deltaSegmentSize; // Get the new size of the segment.

				if (newSegmentSize <= 0) { // Remove a segment if its new limit is 0 after resizing.
					segmentsToRemove.add(segment);
				} else {
					segment.setLimit(newSegmentSize); // Change the limits.
				}
			}
		}

		for (Segment segment : segmentsToRemove) { // Remove the removed segments.
			segmentTable.removeSegment(segment);
		}

		return segmentsToRemove;
	}

	public Segment getSegment(int id) {
		for (Segment segment : segmentTable.getSegments()) {
			if (segment.getId() == id) {
				return segment;
			}
		}
		return null;
	}

	public void segmentTable() {
		System.out.println(segmentTable.toString());
	}

	/**
	 * Simple toString method to return the name of process.
	 */
	@Override
	public String toString() {
		return "P" + pid;
	}
}
