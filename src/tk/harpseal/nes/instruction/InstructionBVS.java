package tk.harpseal.nes.instruction;

import tk.harpseal.nes.CPU;

public class InstructionBVS extends InstructionBranch {

	public InstructionBVS(CPU c, AddressingMode m) {
		super(c, m);
	}

	@Override
	public boolean takeBranch() {
		return cpu.P.getBit(6);
	}

}
