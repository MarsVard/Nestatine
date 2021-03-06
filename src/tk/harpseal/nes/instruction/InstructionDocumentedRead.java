package tk.harpseal.nes.instruction;

import tk.harpseal.nes.CPU;

public abstract class InstructionDocumentedRead extends Instruction {

	public InstructionDocumentedRead(CPU c, AddressingMode m) {
		super(c, m);
	}
	public abstract void applyData(byte b);

	@Override
	public void runCycle(int cycles) {
		if (mode == AddressingMode.IMMEDIATE) {
			switch (cycles) {
			case 0:
				cpu.increasePC();
				break;
			case 1:
				t1 = cpu.getByte(cpu.PC);
				cpu.increasePC();
				applyData(t1);
			}
		}
		if (mode == AddressingMode.ABSOLUTE) {
			switch (cycles) {
			case 0:
				cpu.increasePC();
				break;
			case 1:
				p = cpu.getByte(cpu.PC);
				cpu.increasePC();
				break;
			case 2:
				p |= (cpu.getByte(cpu.PC) << 8);
				cpu.increasePC();
				break;
			case 3:
				applyData(cpu.getByte(p));
			}
		}
		if (mode == AddressingMode.ZEROPAGE) {
			switch (cycles) {
			case 0:
				cpu.increasePC();
				break;
			case 1:
				p = cpu.getByte(cpu.PC);
				cpu.increasePC();
				break;
			case 2:
				applyData(cpu.getByte(p));
			}
		}
		if (mode == AddressingMode.ZEROPAGE_X) {
			switch (cycles) {
			case 0:
				cpu.increasePC();
				break;
			case 1:
				p = cpu.getByte(cpu.PC);
				cpu.increasePC();
				break;
			case 2:
				cpu.getByte(p);
				p = (p + cpu.getX()) & 0xFF;
				break;
			case 3:
				applyData(cpu.getByte(p));
			}
		}
		if (mode == AddressingMode.ABSOLUTE_X) {
			switch (cycles) {
			case 0:
				cpu.increasePC();
				break;
			case 1:
				p = cpu.getByte(cpu.PC);
				cpu.increasePC();
				break;
			case 2:
				t3 = p;
				t3 |= (cpu.getByte(cpu.PC) << 8);
				p = (p + cpu.getX()) & 0xFF;
				p |= (cpu.getByte(cpu.PC) << 8);
				cpu.increasePC();
				break;
			case 3:
				cpu.getByte(p);
				p = (t3 + cpu.getX()) & 0xFFFF;
				cpu.increasePC();
				break;
			case 4:
				applyData(cpu.getByte(p));
			}
		}
		if (mode == AddressingMode.ABSOLUTE_Y) {
			switch (cycles) {
			case 0:
				cpu.increasePC();
				break;
			case 1:
				p = cpu.getByte(cpu.PC);
				cpu.increasePC();
				break;
			case 2:
				t3 = p;
				t3 |= (cpu.getByte(cpu.PC) << 8);
				p = (p + cpu.getX()) & 0xFF;
				p |= (cpu.getByte(cpu.PC) << 8);
				cpu.increasePC();
				break;
			case 3:
				cpu.getByte(p);
				p = (t3 + cpu.getX()) & 0xFFFF;
				cpu.increasePC();
				break;
			case 4:
				applyData(cpu.getByte(p));
			}
		}
		if (mode == AddressingMode.INDIRECT_X) {
			switch (cycles) {
			case 0:
				cpu.increasePC();
				break;
			case 1:
				t1 = cpu.getByte(cpu.PC);
				cpu.increasePC();
				break;
			case 2:
				t1 = (byte) ((t1 + cpu.getX()) & 0xFF);
				break;
			case 3:
				p = cpu.getByte((t1) & 0xFF);
				break;
			case 4:
				p |= (cpu.getByte((t1 + 1) & 0xFF)) << 8;
				break;
			case 5:
				applyData(cpu.getByte(p));
			}
		}
		if (mode == AddressingMode.INDIRECT_Y) {
			switch (cycles) {
			case 0:
				cpu.increasePC();
				break;
			case 1:
				t1 = cpu.getByte(cpu.PC);
				cpu.increasePC();
				break;
			case 2:
				p = cpu.getByte((t1) & 0xFF);
				break;
			case 3:
				p |= (cpu.getByte((t1 + 1) & 0xFF)) << 8;
				t3 = (p + cpu.getY()) & 0xFFFF;
				p = (p & 0xFF00) | (((p & 0xFF) + cpu.getY()) & 0xFF);
				break;
			case 4:
				cpu.getByte(p);
				p = t3;
				break;
			case 5:
				applyData(cpu.getByte(p));
			}
		}
	}

}
