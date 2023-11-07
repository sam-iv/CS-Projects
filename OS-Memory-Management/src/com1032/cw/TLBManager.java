package com1032.cw;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A class to handle TLB.
 * 
 * @author Samuel Ivuerah
 */
public class TLBManager {

	/** The size of the TLB. */
	private static final int tlbSize = 10;

	/** The TLB. */
	private LinkedHashMap<String, Integer> tlb;

	/**
	 * A constructor to intialise the TLB.
	 */
	public TLBManager() {
		tlb = new LinkedHashMap<String, Integer>(tlbSize, 0.75f, true) { // Create the TLB.
			private static final long serialVersionUID = 1L; // Give a serial to silence warnings.

			@Override
			protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) { // Establish the unloading style.
				return size() > tlbSize; // Condition to unload.
			}
		};
	}

	/**
	 * A method to find the content, (physical address), of the TLB, corresponding
	 * to the PID and SID.
	 * 
	 * @param pid The pid of the process.
	 * @param sid The sid of the segment.
	 * @return The content of the TLB, (physical address).
	 */
	public Integer lookup(String pid, String sid) {
		String key = pid + "-" + sid;
		return tlb.getOrDefault(key, null);
	}

	/**
	 * A method to add the physical address to the TLB.
	 * 
	 * @param pid             The PID of the process.
	 * @param sid             The SID of the process.
	 * @param physicalAddress The physical address.
	 * @throws IllegalArgumentException If the PID, or SID is invalid.
	 */
	public void addEntry(String pid, String sid, Integer physicalAddress) throws IllegalArgumentException {
		if (pid.isBlank() || pid.isEmpty()) {
			throw new IllegalArgumentException("Pid is invalid.");
		}
		if (sid.isBlank() || sid.isEmpty()) {
			throw new IllegalArgumentException("Sid is invalid.");
		}
		String key = pid + "-" + sid;
		tlb.put(key, physicalAddress);
	}

	/*
	 * A simple toString method to output the contents of the TLB.
	 */
	@Override
	public String toString() {
		return tlb.toString();
	}

}
