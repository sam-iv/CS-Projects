package com1032.cw;

import java.util.ArrayList;

/**
 * A class to model a table of segments, belonging to a process.
 * 
 * @author Samuel Ivuerah
 */
public class SegmentTable {

	/** A list of segments belonging to a process. */
	private ArrayList<Segment> segments;

	/**
	 * Constructor to instantiate a SegmentTable object to produce a table of list
	 * of segments.
	 * 
	 * @param seg The list of segments belonging to a process.
	 * @throws IllegalArgumentException If the list of segments, (seg), is invalid.
	 */
	public SegmentTable(ArrayList<Segment> seg) throws IllegalArgumentException {
		if (seg == null) {
			throw new IllegalArgumentException("Invalid list of segments.");
		}
		this.segments = seg;
	}

	/**
	 * A method to add a new segment to the segment table.
	 * 
	 * @param segment The new segment to be added.
	 * @throws IllegalArgumentException If the segment is invalid or the segment
	 *                                  already exists inside the table, (list).
	 */
	public void addSegment(Segment segment) throws IllegalArgumentException {
		if (segment == null) {
			throw new IllegalArgumentException("Segment is invalid.");
		}
		if (segments.contains(segment)) {
			throw new IllegalArgumentException("Segment is already in the table.");
		}
		segments.add(segment);
	}

	/**
	 * A method to remove a segment from the segment table.
	 * 
	 * @param segment The segment to be removed from the table, (list).
	 * @throws IllegalArgumentException If the segment is invalid or is not in the
	 *                                  table, (list).
	 */
	public void removeSegment(Segment segment) throws IllegalArgumentException {
		if (segment == null) {
			throw new IllegalArgumentException("Segment is invalid.");
		}
		if (!segments.contains(segment)) {
			throw new IllegalArgumentException("Segment is not in the table.");
		}
		segments.remove(segment);
	}

	public ArrayList<Segment> getSegments() {
		return segments;
	}

	/**
	 * A toString method to display the table in a pretty format.
	 * 
	 * @return A string to be displayed to console.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nSID | Base | Limit | Valid-Invalid | Permissions\n");
		for (Segment segment : segments) {
			boolean valid = segment.isValid(); // Acquire the valid-invalid bit.
			int val = (valid) ? 1 : 0; // Conditional operator to choose between 1 or 0, if the bit is true or false.
			String formattedSegment = String.format("%3d | %4d | %5d | %13d | %10s\n", segment.getId(),
					segment.getBase(), segment.getLimit(), val, segment.getPermissions());
			sb.append(formattedSegment);
		}
		return sb.toString(); // Return the string to be displayed.
	}

}
