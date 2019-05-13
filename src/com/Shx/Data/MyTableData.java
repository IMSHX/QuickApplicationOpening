package com.Shx.Data;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyTableData extends AbstractTableModel {
    private String[] head;
    private Object[][] objects;
    private Class[] typeArray;
    private int headLength;
    private int objectsLength;
    public MyTableData(String[] head,Object[][] objects,Class[] typeArray) {
        this.head = head;
        this.headLength = head.length;
        this.objects = objects;
        this.objectsLength = objects.length;
        this.typeArray = typeArray;
    }
    public String getColumnName(int column){
        return head[column];
    }



    @Override
    public int getRowCount() {
        return objects.length;
    }

    @Override
    public int getColumnCount() {
        return head.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return typeArray[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
//        System.out.println(rowIndex+":"+columnIndex);

        return objects[rowIndex][columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 1||columnIndex==3){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        System.out.println(rowIndex+":"+columnIndex);
        objects[rowIndex][columnIndex] = aValue;
        System.out.println(aValue);
    }
    public List<Boolean> getChoice(){
        List<Boolean> UserChoice = new ArrayList<>();
        for(int i = 0;i<objectsLength;i++){
            UserChoice.add((Boolean)this.getValueAt(0,i));
        }
        return UserChoice;
    }

    public PropertiesData getOldValueAt(int Number){
        return new PropertiesData((String)objects[Number][2],(String) objects[Number][3]);
    }
    public PropertiesData getNewValueAt(int Number){
        return new PropertiesData((String)getValueAt(Number,2),(String) getValueAt(Number,3));
    }

    public int getHeadLength() {
        return headLength;
    }

    public int getObjectsLength() {
        return objectsLength;
    }

}
