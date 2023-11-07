package com1032.cw;

/**
 * A class to model a segment, for a process.
 * 
 * @author Samuel Ivuerah
 */
public class Segment {

	/** The ID (SID) for the segment. */
	private int id;

	/** The base address of the segment. */
	private int base;

	/** The limit of the segment. */
	private int limit;

	/** The valid-invalid bit of the segment. */
	private boolean valid = false;

	/** The permissions of the segment. */
	private String permissions;

	/**
	 * Constructor to instantiate a Segment object, allowing for a specified SID,
	 * base address, limit, and permissions.
	 * 
	 * @param segmentID The segment ID (SID) for the segment
	 * @param base      The base address of the segment.
	 * @param limit     The limit of the segment.
	 * @param perm      The permissions of the segment.
	 * @throws IllegalArgumentException If the segment ID, base address, or limit is
	 *                                  invalid.
	 */
	public Segment(int segmentID, int base, int limit, String perm) throws IllegalArgumentException {
		if (segmentID < 0) {
			throw new IllegalArgumentException("Invalid SID.");
		}
		if (base < 0) {
			throw new IllegalArgumentException("Invalid Base.");
		}
		if (limit < 0) {
			throw new IllegalArgumentException("Invalid Limit.");
		}
		this.id = segmentID;
		this.base = base;
		this.limit = limit;
		permissions = perm;
	}

	public String getPermissions() {
		return this.permissions;
	}

	/**
	 * Sets the permission(s) of the segment, where "r--" is read-only, "-w-", "r-x"
	 * is read and execute, and "rwx" means read write and execute.
	 * 
	 * @param permissions The permissions to set to the segment.
	 * @throws IllegalArgumentException if the permission(s) is invalid.
	 */
	public void setPermissions(String permissions) throws IllegalArgumentException {
		// Verify that permissions input is valid
		if (!permissions.equals("r--") && !permissions.equals("-w-") && !permissions.equals("r-x")
				&& !permissions.equals("rwx")) {
			throw new IllegalArgumentException("Invalid permissions input.");
		}
		this.permissions = permissions;
	}

	/**
	 * Checks if the segment has the read permission.
	 * 
	 * @return true if the segment has the read permission, false otherwise.
	 */
	public boolean hasReadPermission() {
		return this.permissions.charAt(0) == 'r';
	}

	public int getId() {
		return id;
	}

	public int getBase() {
		return base;
	}

	public int getLimit() {
		return limit;
	}

	/**
	 * A setter to set the limit of a segment.
	 * 
	 * @param limit The new limit of the segment.
	 * @throws IllegalArgumentException If the limit is invalid.
	 */
	public void setLimit(int limit) throws IllegalArgumentException {
		if (limit < 0) {
			throw new IllegalArgumentException("Invalid Limit.");
		}
		this.limit = limit;
	}

	/**
	 * A setter to set the base address of a segment.
	 * 
	 * @param base The new base address of the segment.
	 * @throws IllegalArgumentException If the base address is invalid.
	 */
	public void setBase(int base) throws IllegalArgumentException {
		if (base < 0) {
			throw new IllegalArgumentException("Invalid Base.");
		}
		this.base = base;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/**
	 * Simple toString method to return a string name to represent this segment.
	 */
	@Override
	public String toString() {
		return "S" + id;
	}
}
