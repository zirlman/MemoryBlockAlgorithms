package org.unibl.etf.MemoryBlockAlgorithms;

import java.util.ArrayList;
import java.util.Scanner;

public class BestFit {
    private static class MemoryBlock {
        private int capacity;
        private int availableStorage;

        public MemoryBlock(int capacity) {
            this.capacity = capacity;
            this.availableStorage = capacity;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public int getAvailableStorage() {
            return availableStorage;
        }

        public void setAvailableStorage(int availableStorage) {
            this.availableStorage = availableStorage;
        }

        @Override
        public String toString() {
            return "Block capacitiy: " + capacity + ", available: " + availableStorage;
        }
    }

    private static ArrayList<MemoryBlock> memory = new ArrayList<>();

    private static void initializeBlocks(String[] blockSize) {
        for (String bs : blockSize)
            memory.add(new MemoryBlock(Integer.valueOf(bs)));
    }

    private static void loadBlocks(String[] blocks) {
        int tmp, index = -1;
        for (String blockSize : blocks) {
            int freeSpace = Integer.MAX_VALUE;
            int counter = 0;
            for (MemoryBlock block : memory) {
                tmp = block.getAvailableStorage() - Integer.valueOf(blockSize);
                if (tmp >= 0 && tmp < freeSpace) {
                    freeSpace = tmp;
                    index = counter;
                }
                ++counter;
            }
            if (freeSpace != Integer.MAX_VALUE)
                memory.get(index).setAvailableStorage(freeSpace);
            else
                System.out.println("Block size (" + blockSize + ") too big!");
        }
    }

    private static void printMemory() {
        int counter = 0;
        for (MemoryBlock block : memory)
            System.out.println(++counter + ". " + block);
    }

    // 512,128,256,1028,64,32,2048 500,78,1028,600,400,10,99999
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
