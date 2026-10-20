package Restuarant;

import java.io.Serializable;
import java.util.Random;
import javax.swing.table.DefaultTableModel;

class Food implements Serializable {
 
    private String id;
    private String name;
    private String size;
    private double price;
 
    public Food(String id, String name, String size, double price) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
    }
 
    public String getId() {
        return id;
    }
 
    public String getName() {
        return name;
    }
 
    public String getSize() {
        return size;
    }
 
    public double getPrice() {
        return price;
    }
 
    @Override
    public String toString() {
        return id + "," + name + "," + size + "," + price;
    }
}
 
class Node {
 
    private Food data;
    private Node next;
    private Node prev;
 
    public Node(Food food) {
        data = food;
        next = null;
        prev = null;
    }
 
    public void setNext(Node n) {
        next = n;
    }
 
    public Node getNext() {
        return next;
    }
 
    public void setPrev(Node n) {
        prev = n;
    }
 
    public Node getPrev() {
        return prev;
    }
 
    public Food getData() {
        return data;
    }
 
    @Override
    public String toString() {
        return data.toString();
    }
}
 
class LinkedList {
 
    private Node head;
    private Node tail;
    private int count;
 
    public LinkedList() {
        head = null;
        tail = null;
        count = 0;
    }
 
    public void addLast(Node n) {
 
        if (count == 0) {
 
            head = n;
            tail = n;
 
        } else {
 
            n.setPrev(tail);
            tail.setNext(n);
            tail = n;
        }
 
        count++;
    }
 
    public void addFirst(Node n) {
 
        if (count == 0) {
 
            head = n;
            tail = n;
 
        } else {
 
            n.setNext(head);
            head.setPrev(n);
            head = n;
        }
 
        count++;
    }
 
    public Node deleteFirst() {
 
        if (head == null) {
            return null;
        }
 
        Node deleted = head;
 
        if (head == tail) {
 
            head = null;
            tail = null;
 
        } else {
 
            head = head.getNext();
            head.setPrev(null);
            deleted.setNext(null);
        }
 
        count--;
 
        return deleted;
    }
 
    public Node deleteLast() {
 
        if (tail == null) {
            return null;
        }
 
        Node deleted = tail;
 
        if (head == tail) {
 
            head = null;
            tail = null;
 
        } else {
 
            tail = tail.getPrev();
            tail.setNext(null);
            deleted.setPrev(null);
        }
 
        count--;
 
        return deleted;
    }
 
    public Node get(int index) {
        if (index >= count) {
            return null;
        }
 
        Node p = head;
        for (int i = 0; i < index; i++) {
            p = p.getNext();
        }
        return p;
    }
 
    public Node search(String key) {
 
        Node p = head;
 
        while (p != null) {
 
            if (p.getData().getId().equals(key)) {
                return p;
            }
 
            p = p.getNext();
        }
 
        return null;
    }
 
    public boolean delete(String key) {
 
        Node p = search(key);
 
        if (p == null) {
            return false;
        }
 
        if (p == head && p == tail) {
 
            head = null;
            tail = null;
 
        } else if (p == head) {
 
            head = p.getNext();
            head.setPrev(null);
 
        } else if (p == tail) {
 
            tail = p.getPrev();
            tail.setNext(null);
 
        } else {
 
            Node previous = p.getPrev();
            Node next = p.getNext();
 
            previous.setNext(next);
            next.setPrev(previous);
        }
 
        p.setNext(null);
        p.setPrev(null);
 
        count--;
 
        return true;
    }
 
    public boolean isEmpty() {
        return count == 0;
    }
 
    public int size() {
        return count;
    }
 
    public Node getHead() {
        return head;
    }
 
    public Node getTail() {
        return tail;
    }
}
 
// ข้อ 1: Customer
class Customer implements Serializable {
 
    private String tableNo;
    private String name;
 
    public Customer(String tableNo, String name) {
        this.tableNo = tableNo;
        this.name = name;
    }
 
    public String getTableNo() {
        return tableNo;
    }
 
    public String getName() {
        return name;
    }
 
    @Override
    public String toString() {
        return tableNo + "," + name;
    }
}
 
// Stack: push เพิ่มท้ายลิสต์, pop เอาออกจากท้ายลิสต์
class Stack {
 
    private LinkedList list = new LinkedList();
 
    public void push(Node n) {
        list.addLast(n);
    }
 
    public Node pop() {
        return list.deleteLast();
    }
 
    public boolean isEmpty() {
        return list.isEmpty();
    }
 
    public int size() {
        return list.size();
    }
 
    public LinkedList getList() {
        return list;
    }
}
 
// Queue: enqueue เพิ่มท้ายลิสต์, dequeue เอาออกจากหัวลิสต์
class Queue {
 
    private LinkedList list = new LinkedList();
 
    public void enqueue(Node n) {
        list.addLast(n);
    }
 
    public Node dequeue() {
        return list.deleteFirst();
    }
 
    public boolean delete(String foodId) {
        return list.delete(foodId);
    }
 
    public boolean isEmpty() {
        return list.isEmpty();
    }
 
    public int size() {
        return list.size();
    }
 
    public LinkedList getList() {
        return list;
    }
}
 
// ข้อ 3: Order
class Order {
 
    private String tableNo;
    private Stack foods;
 
    public Order(String tableNo) {
        this.tableNo = tableNo;
        this.foods = new Stack();
    }
 
    public String getTableNo() {
        return tableNo;
    }
 
    public Stack getFoods() {
        return foods;
    }
}
 
class Utilities {
 
    static Random r;
    static String chars = "abcdefghijklmnopqrstuvwxyz";
 
    public static Food generateFood() {
        r = new Random();
        String id = "" + (r.nextInt(900) + 100);
        String name = "";
        for (int i = 0; i < r.nextInt(10) + 4; i++) {
            name += chars.charAt(r.nextInt(chars.length()));
        }
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        String sizes = "SML";
        String size = "" + sizes.charAt(r.nextInt(sizes.length()));
        double price = (r.nextInt(10) + 1) * 100.;
 
        return new Food(id, name, size, price);
    }
 
    // ข้อ 6: generateCustomer
    public static Customer generateCustomer() {
        r = new Random();
        String tableNo = "" + "ABC".charAt(r.nextInt(3)) + (r.nextInt(9) + 1);
        String name = "";
        int len = r.nextInt(5) + 3;
        for (int i = 0; i < len; i++) {
            name += chars.charAt(r.nextInt(chars.length()));
        }
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return new Customer(tableNo, name);
    }
 
}

public class Midterm extends javax.swing.JFrame {

    public static LinkedList menu;
    public static Customer[] customers;
    public static Order order;
    public static Queue cookingQueue, readyQueue;

    public static String[] headerFood = {"ID", "Name", "Size", "Price"};
    public static String[] headerCust = {"Table No.", "Name"};

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Midterm.class.getName());

    public Midterm() {
        initComponents();
        menu = new LinkedList();
        cookingQueue = new Queue();
        readyQueue = new Queue();

        Object[][] data = null;
        DefaultTableModel model1 = new DefaultTableModel(data, headerFood);
        jTable1.setModel(model1);
        DefaultTableModel model2 = new DefaultTableModel(data, headerCust);
        jTable2.setModel(model2);
        DefaultTableModel model3 = new DefaultTableModel(data, headerFood);
        jTable3.setModel(model3);
        DefaultTableModel model4 = new DefaultTableModel(data, headerFood);
        jTable4.setModel(model4);
        DefaultTableModel model5 = new DefaultTableModel(data, headerFood);
        jTable5.setModel(model5);

    }
    
    private void showFoods(javax.swing.JTable table, LinkedList list) {
        Object[][] data = new Object[list.size()][];
        Node p = list.getHead();
        for (int i = 0; i < list.size(); i++) {
            data[i] = p.toString().split(",");
            p = p.getNext();
        }
        table.setModel(new DefaultTableModel(data, headerFood));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Restuarant: 07123456-Alex B");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("1. LoadMenu");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("4. GenCustomer");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setText("5. GenOrder");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setText("6. Submit");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setText("7. Undo");
        jButton5.addActionListener(this::jButton5ActionPerformed);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTable5);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Cooking Queue");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Ready Queue");

        jTextField1.setText("Customer:");

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setText("8. Cook");
        jButton6.addActionListener(this::jButton6ActionPerformed);

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 51, 204));
        jButton7.setText("2. GenFood");
        jButton7.addActionListener(this::jButton7ActionPerformed);

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton8.setText("3. SaveMenu");
        jButton8.addActionListener(this::jButton8ActionPerformed);

        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton9.setText("Close");
        jButton9.addActionListener(this::jButton9ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(32, 32, 32)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton9))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton9)
                            .addComponent(jButton8)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addGap(7, 7, 7)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton5))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(jLabel1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Cook button
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Node n = cookingQueue.dequeue();
        if (n == null) {
            return;
        }
        readyQueue.enqueue(new Node(n.getData()));
        showFoods(jTable4, cookingQueue.getList());
        showFoods(jTable5, readyQueue.getList());
    }//GEN-LAST:event_jButton6ActionPerformed

    // GenFood button
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        for (int i = 0; i < 5; i++) {
            menu.addLast(new Node(Utilities.generateFood()));
        }
        Object[][] data = new Object[menu.size()][];
 
        Node p = menu.getHead();
        for (int i = 0; i < menu.size(); i++) {
            data[i] = p.toString().split(",");
            p = p.getNext();
        }
 
        DefaultTableModel model = new DefaultTableModel(data, headerFood);
        jTable1.setModel(model);
    }//GEN-LAST:event_jButton7ActionPerformed

    // Close button
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton9ActionPerformed

    // LoadMenu button
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try (java.io.ObjectInputStream in
                = new java.io.ObjectInputStream(new java.io.FileInputStream("menu.ser"))) {
            menu = new LinkedList();
            Object obj = in.readObject();
 
            if (obj instanceof Food[]) {               // เก็บมาเป็นอาร์เรย์
                Food[] arr = (Food[]) obj;
                for (int i = 0; i < arr.length; i++) {
                    menu.addLast(new Node(arr[i]));
                }
            } else if (obj instanceof Integer) {       // เขียนจำนวนก่อน แล้วตามด้วย Food ทีละตัว
                int n = (Integer) obj;
                for (int i = 0; i < n; i++) {
                    menu.addLast(new Node((Food) in.readObject()));
                }
            } else if (obj instanceof Food) {          // เขียน Food ต่อกันไปเรื่อย ๆ
                menu.addLast(new Node((Food) obj));
                try {
                    while (true) {
                        menu.addLast(new Node((Food) in.readObject()));
                    }
                } catch (java.io.EOFException e) {
                    // อ่านจนจบไฟล์
                }
            }
            showFoods(jTable1, menu);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Load error: " + e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // SaveMenu button
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try (java.io.ObjectOutputStream out
                = new java.io.ObjectOutputStream(new java.io.FileOutputStream("menu.ser"))) {
            Food[] arr = new Food[menu.size()];
            Node p = menu.getHead();
            for (int i = 0; i < arr.length; i++) {
                arr[i] = p.getData();
                p = p.getNext();
            }
            out.writeObject(arr);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Save error: " + e);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    // GenCustomer button
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        customers = new Customer[3];
        Object[][] data = new Object[3][];
        for (int i = 0; i < 3; i++) {
            customers[i] = Utilities.generateCustomer();
            data[i] = customers[i].toString().split(",");
        }
        jTable2.setModel(new DefaultTableModel(data, headerCust));
    }//GEN-LAST:event_jButton2ActionPerformed

    // GenOrder button
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (customers == null || menu.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "ต้อง GenCustomer และมีเมนูก่อน");
            return;
        }
        Random r = new Random();
        Customer c = customers[r.nextInt(customers.length)];
        order = new Order(c.getTableNo());
        for (int i = 0; i < 4; i++) {
            Food f = menu.get(r.nextInt(menu.size())).getData();
            order.getFoods().push(new Node(f));
        }
        jTextField1.setText("Customer: " + c.toString());
        showFoods(jTable3, order.getFoods().getList());
    }//GEN-LAST:event_jButton3ActionPerformed

    // Submit button
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (order == null) {
            return;
        }
        Node p = order.getFoods().getList().getHead();
        while (p != null) {
            cookingQueue.enqueue(new Node(p.getData()));
            p = p.getNext();
        }
        showFoods(jTable4, cookingQueue.getList());
    }//GEN-LAST:event_jButton4ActionPerformed

    // Undo button
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (order == null) {
            return;
        }
        Node n = order.getFoods().pop();
        if (n == null) {
            return;
        }
        cookingQueue.delete(n.getData().getId());
        showFoods(jTable3, order.getFoods().getList());
        showFoods(jTable4, cookingQueue.getList());
    }//GEN-LAST:event_jButton5ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new Midterm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
