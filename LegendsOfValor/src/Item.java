/*
 * Item.java
 * by Lily Jihyun Son and Grace Elias
 * 
 * Abstract class that represents an Item which has various attributes and accessors. 
 */
public abstract class Item{
    // protected variables for item class
    protected String name;
    protected int price;
    protected int requiredLevel;
    protected int uses;
    protected int maxUses;

    // public constructor for item class
    public Item(String name, int price, int requiredLevel, int uses){
        this.name = name;
        this.price = price;
        this.requiredLevel = requiredLevel;
        this.uses = uses;
        this.maxUses = uses;
    }

    // accessor methods of item class
    public String getName(){
        return name;
    }

    public int getPrice(){
        return price;
    }

    public int getRequiredLevel(){
        return requiredLevel;
    }

    public int getUses(){
        return uses;
    }

    public boolean isUsable(){
        return uses>0;
    }

    public void use(){
        if(uses > 0){
            uses --;
        }
    }

    public void repair(){
        uses = maxUses;
    }

    public abstract void display();
}