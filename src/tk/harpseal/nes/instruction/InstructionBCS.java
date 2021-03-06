package tk.harpseal.nes.instruction;

import tk.harpseal.nes.CPU;

public class InstructionBCS extends InstructionBranch {

	public InstructionBCS(CPU c, AddressingMode m) {
		super(c, m);
	}

	@Override
	public boolean takeBranch() {
		return cpu.P.getBit(0);
	}

}
