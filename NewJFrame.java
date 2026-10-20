package javaapplication10;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

class Pet implements Serializable{
    int id;
    String name;
    String type;
    int age;

    public Pet(int id, String name, String type, int age) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + type + "," + age;
    }
    
}

class Adopter implements Serializable{
        int id;
    String name;
    String gender;
    int age;
    double salary;
    Pet pet;

    public Adopter(int id, String name, String gender, int age, double salary) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        String Petname = "null";
        if(pet!=null){
            Petname = pet.toString();
        }
        return id+","+name+","+gender+","+age+","+salary+","+Petname;
    }
    
}

class PNode implements Serializable{
   Pet data;
   PNode next, prev;

    public PNode(Pet data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
   
}

class ANode implements Serializable{
   Adopter data;
   ANode next, prev;

    public ANode(Adopter data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
   
}
 
class LinkedList implements Serializable{
    PNode head;
    PNode tail;
    int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    public void add(Pet a){
        PNode node = new PNode(a);
        if(head==null){
            head=tail=node;
        }
        else{
            tail.next =node;
            node.prev =tail;
            tail =node;
        }
        size++;
    }
    
    public Pet remove(){
        if(isEmpty()){
            return null;
        }
        Pet temp = tail.data;
        tail = tail.prev;
        
        if(tail == null){
        head = null;
        } else {
        tail.next = null;
        }
        
        size--;
        
        return temp;
    }
    
    boolean isEmpty(){
        return head==null;
    }
    
    int size(){
        return size;
    }
    
    PNode getHead(){
        return head;
    }
    
    Pet[] toArray(){
        Pet[] arr = new Pet[size];
        PNode a = head;
        int i=0;
        while(a!=null){
            arr[i]=a.data;
            a = a.next;
            i++;
        }
        return arr;
    }
}

class Stack implements Serializable{
    ANode top;
    int size;
    
    public Stack(){
        top = null;
        size =0;
    }
    
    void push(Adopter A){
        ANode node = new ANode(A);
        node.next = top;
        top = node;
        size++;
    }
    
    Adopter pop(){
        if(isEmpty()){
            return null;
        }
        Adopter temp = top.data;
        top = top.next;
        size--;
        return temp;
    }
    
    boolean isEmpty(){
        return top == null;
    }
    
    int size(){
        return size;
    }
    
    ANode getTop(){
        return top;
    }
    
    Adopter[] toArray(){
        Adopter[] arr = new Adopter[size];
        ANode a = top;
        int i=0;
        while(a!=null){
            arr[i]=a.data;
            a = a.next;
            i++;
        }
        return arr;
    }
}

class Queue implements Serializable{
    ANode front;
    ANode last;
    int size;
    
    Queue(){
        front =null;
        last =null;
        size=0;
    }
    
    void offer(Adopter a){
        ANode node = new ANode(a);
        if(last==null){
            front=last=node;
        }
        else{
            last.next =node;
            node.prev = last;
            last =node;
        }
        size++;
    }
    
    Adopter poll(){
        if(isEmpty()){
            return null;
        }
        Adopter temp =front.data;
        front = front.next;
        if(front!=null){
            front.prev = null;
        }
        else{
            last=null;
        }
        size--;
        return temp;
    }
    
    boolean isEmpty(){
        return front==null;
    }
    
    int size(){
        return size;
    }
    
    ANode getFront(){
        return front;
    }
    Adopter[] toArray(){
        Adopter[] arr = new Adopter[size];
        ANode a = front;
        int i=0;
        while(a!=null){
            arr[i]=a.data;
            a = a.next;
            i++;
        }
        return arr;
    }
    void sortByid(){
        Adopter[] arr =toArray();
        
        for(int j=0;j<arr.length-1;j++){
            for(int i=0;i<arr.length-1;i++){
                if(arr[i].getId()>arr[i+1].getId()){
                    Adopter temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                }
            }
        }
        clear();
        for(int i=0;i<arr.length;i++){
            offer(arr[i]);
        }
    }
    void clear(){
        front =null;
        last =null;
        size =0;
    }
}

public class NewJFrame extends javax.swing.JFrame {
    
    Random r = new Random();    
    private LinkedList list = new LinkedList();
    private Stack stack = new Stack();
    private Queue queue = new Queue();
    
    private Pet currentPet =null;
    private Adopter currentAdopter =null;
    
    private Pet genPString(){
    String str = "abcdefghijklmnopqrstuvwxyz";
    String[] type = {"cat","dog"};

    
    int id = r.nextInt(90)+10;
    
    String Name="";
    for(int i=0;i<r.nextInt(5)+2;i++){
        Name+= str.charAt(r.nextInt(26));
    }
    
    String Type= type[r.nextInt(type.length)];
    
    int age = r.nextInt(20)+2;
    
    return new Pet(id,Name,Type,age);
}
    
    private Adopter genAString(){
    String str = "abcdefghijklmnopqrstuvwxyz";
    String[] type = {"male","female"};
    
    int id = r.nextInt(90)+10;
    
    String Name="";
    for(int i=0;i<r.nextInt(5)+2;i++){
        Name+= str.charAt(r.nextInt(26));
    }
    
    String gender= type[r.nextInt(type.length)];
    
    int age = r.nextInt(20)+2;
    
    double salary = r.nextInt(20000)+1000.0;
    
    return new Adopter(id,Name,gender,age,salary);
}
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(NewJFrame.class.getName());
    
    public NewJFrame() {
        initComponents();
    }

    private void updateTable1(){
        DefaultTableModel model =(DefaultTableModel)jTable1.getModel();
        model.setRowCount(0);
        
        Pet[] arr = list.toArray();
         for (int i = 0; i < arr.length; i++) { 
               model.addRow(new Object[]{ 
            arr[i].getId(), arr[i].getName(), arr[i].getType(), arr[i].getAge() 
        }); 
        }
    }
    
    private void updateTable2(){
        DefaultTableModel model =(DefaultTableModel)jTable2.getModel();
        model.setRowCount(0);
        
        Adopter[] arr = stack.toArray();
         for (int i = 0; i < arr.length; i++) { 
               model.addRow(new Object[]{ 
            arr[i].getId(), arr[i].getName(), arr[i].getGender(), arr[i].getAge(),arr[i].getSalary() 
        }); 
        }
    }
    
    private void updateTable3(){
        DefaultTableModel model =(DefaultTableModel)jTable3.getModel();
        model.setRowCount(0);
        
        Adopter[] arr = queue.toArray();
         for (int i = 0; i < arr.length; i++) { 
               model.addRow(new Object[]{ 
            arr[i].getId(), arr[i].getName(), arr[i].getGender(), arr[i].getAge(),arr[i].getSalary(),arr[i].getPet() 
        }); 
        }
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
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jButton1.setText("Genpet");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Addpet");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("RemovePet");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("GenAdopter");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("AddAdopter");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("RemoveAdopter");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("AssignPet");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Sort");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Save");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Load");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(jTextField2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4)
                            .addComponent(jButton1)
                            .addComponent(jButton7)
                            .addComponent(jButton9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton10)
                            .addComponent(jButton8)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2)
                                    .addComponent(jButton5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3)
                                    .addComponent(jButton6))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7)
                            .addComponent(jButton8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton9)
                            .addComponent(jButton10)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        currentPet = genPString();
        jTextField1.setText(currentPet.toString());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         if (currentPet == null) { 
            jTextField1.setText("Press GenPet"); 
            return; 
        } 
        list.add(currentPet); 
        updateTable1(); 
        currentPet = null; 
        jTextField1.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        list.remove();
        updateTable1();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        currentAdopter = genAString();
        jTextField2.setText(currentAdopter.toString());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (currentAdopter == null) { 
            jTextField2.setText("Press GenAdopter"); 
            return; 
        } 
        stack.push(currentAdopter); 
        updateTable2(); 
        currentAdopter = null; 
        jTextField2.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        stack.pop();
        updateTable2();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(list.isEmpty()||stack.isEmpty()){
        return;    
        }
        Adopter x = stack.pop(); 
        Pet y = list.remove();
        x.setPet(y);
        queue.offer(x);
        updateTable1(); 
        updateTable2(); 
        updateTable3();  
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        queue.sortByid();
        updateTable3();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
         try { 
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.ser")); 
            out.writeObject(queue); 
            out.close(); 
            JOptionPane.showMessageDialog(this, "Save data.ser Success"); 
        } catch (Exception e) { 
            JOptionPane.showMessageDialog(this, "Save error: " + e.getMessage()); 
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
         try { 
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.ser")); 
            queue = (Queue) in.readObject(); 
            in.close(); 
            updateTable3(); 
            JOptionPane.showMessageDialog(this, "Load data.ser Suc"); 
        } catch (Exception e) { 
            JOptionPane.showMessageDialog(this, "Load error: " + e.getMessage()); 
        } 

    }//GEN-LAST:event_jButton10ActionPerformed


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new NewJFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
