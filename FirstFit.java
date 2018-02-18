package org.unibl.etf.MemoryBlockAlgorithms;

import java.util.ArrayList;
import java.util.Scanner;

public class FirstFit {
    private static class MemoryBlock {
        private int capacity;
        private int available;

        public MemoryBlock(int capacity) {
            this.capacity = capacity;
            this.available = capacity;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public int getAvailable() {
            return available;
        }

        public void setAvailable(int available) {
            this.available = available;
        }

        @Override
        public String toString() {
            return "Block capacitiy: " + capacity + ", available: " + available;
        }

    }

    private static ArrayList<MemoryBlock> memory = new ArrayList<>();

    private static void initializeBlocks(String[] blockSize) {
        for (String bs : blockSize)
            memory.add(new MemoryBlock(Integer.valueOf(bs)));
    }

    private static void loadBlocks(String[] blocks) {
        int freeStorage = -1;
        for (String blockSize : blocks) {
            for (int i = 0; i < memory.size(); ++i) {
                freeStorage = memory.get(i).getAvailable() - Integer.valueOf(blockSize);
                if (freeStorage >= 0) {
                    memory.get(i).setAvailable(freeStorage);
                    break;
                }
            }
            if (freeStorage < 0)
                System.out.println("Block size (" + Integer.valueOf(blockSize) + ") too big!");
        }
    }

    private static void printMemory() {
        int counter = 0;
        for (MemoryBlock block : memory)
            System.out.println(++counter + ". " + block);
    }

    // 128,256,512,2048,1028,64,32 500,78,1028,600,400,10,30,99999
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter initialization block size (128,256,512,...): ");
        initializeBlocks(scanner.next().split(","));
        System.out.print("Enter block size (128,256,512,...): ");
        String input = scanner.next();
        String[] blocks = input.split(",");
        loadBlocks(blocks);
        printMemory();
    }
}
