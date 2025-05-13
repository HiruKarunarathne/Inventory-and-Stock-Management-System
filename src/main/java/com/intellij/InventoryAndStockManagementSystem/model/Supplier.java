package com.intellij.InventoryAndStockManagementSystem.model;

    public class Supplier implements Comparable<Supplier> {
        private String id;
        private String name;
        private String address;

        public Supplier(String id, String name, String address) {
            this.id = id;
            this.name = name;
            this.address = address;
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public String getAddress() { return address; }

        public void setId(String id) { this.id = id; }
        public void setName(String name) { this.name = name; }
        public void setAddress(String address) { this.address = address; }

        @Override
        public int compareTo(Supplier other) {
            return this.name.compareToIgnoreCase(other.name);
        }

        @Override
        public String toString() {
            return id + "," + name + "," + address;
        }

        public static Supplier fromString(String line) {
            String[] parts = line.split(",");
            return new Supplier(parts[0], parts[1], parts[2]);
        }
    }


