package com.mycompany.a1.gameobjects.fixed;

import com.codename1.charts.util.ColorUtil;

public class Flag extends Fixed {
	private int sequenceNumber;

	public Flag(int sequenceNum) {
		// Super should be first line of code in constructor
		
		// all flags are 40 size and blue in color
		super(40, ColorUtil.BLUE);
		this.sequenceNumber = sequenceNum;
	}

	// get flag Sequence number
	public int getSequenceNumber() {
		return this.sequenceNumber;
	}

	// Flags are not allowed to change their color once they are created
	public void setColor(int color) {
	}

	// we do not need to set the flag sequence number because it is set
	// when the flag is first created

	// get's the gameObject.toString() and adds a little description more as well
	@Override
	public String toString() {
		String parentDesc = super.toString();

		String seqNum = "seqNum=" + this.getSequenceNumber();

		return "Flag:  " + parentDesc + " " + seqNum;
	}
}
