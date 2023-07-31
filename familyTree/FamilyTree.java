package familyTree;

import familyTree.human.Human;

import java.io.Serializable;
import java.util.*;

public class FamilyTree implements Serializable,Iterable<Human>

 {
    private long humansId;
    private List<Human> humanList;

    public FamilyTree(List<Human> humanList){this.humanList=humanList;}
    
    public FamilyTree() {
        this(new ArrayList<>());
    }

    public boolean add(Human human){
        if(human==null){
            return false;
        }
        if(!humanList.contains(human)){
            humanList.add(human);
            human.setId(humansId++);
            addToParents(human);
            addToChildren(human);
            return true;
        }
        return false;
    }
    private void addToParents(Human human){
        for(Human parent: human.getParents()){
            parent.addChild(human);
        }
    }
    private void addToChildren(Human human){
        for(Human child:human.getChildren()){
            child.addParent(human);
        }
    }
    public List<Human>getSibLings(int id){
        Human human=getById(id);
        if(human==null){
            return null;
        }
        List<Human> res=new ArrayList<>();
        for(Human parent:human.getParents()){
            for(Human child:human.getChildren()){
                if(!child.equals(human)){
                    res.add(child);
                }
            }
        }
        return res;
    }
    public List<Human>getByName(String name){
        List<Human> res=new ArrayList<>();
        for(Human human:humanList){
            if(human.getName().equalsIgnoreCase(name)){
                res.add(human);
            }
        }
        return res;
    }
    public boolean setWedding(long humanId1,long humanId2){
        if(checkId(humanId1)&&checkId(humanId2)) {
            Human human1=getById(humanId1);
            Human human2=getById(humanId2);
            if(human1.getSpouse()==null&&human2.getSpouse()==null){
            human1.setSpouse(human2);
            human2.setSpouse(human1);
            }else{
                return false;
            }
        }
        return false;
    }
    public boolean setDivorce(long humanId1,long humanId2){
        if(checkId(humanId1)&&checkId(humanId2)) {
            Human human1=getById(humanId1);
            Human human2=getById(humanId2);
            if(human1.getSpouse()!=null&&human2.getSpouse()!=null){
            human1.setSpouse(null);
            human2.setSpouse(null);
            }else{
                return false;
            }
        }
        return false;
    }

    public boolean remove(long humansId){
        if(checkId(humansId)){
            Human e=getById(humansId);
            return humanList.remove(e);
        }
        return false;
    }
    private boolean checkId(long id){
        if(id>=humansId || id<0){
            return false;
        }
        for(Human human:humanList){
            if(human.getId()==id){
                return true;
            }
        }
        return false;
    }
    public Human getById(long id){
        for (Human human:humanList){
            if(human.getId()==id){
                return human;
            }
        }
        return null;
    }
    public String getInfo(){
        StringBuilder sb=new StringBuilder();
        sb.append("В дереве");
        sb.append(humanList.size());
        sb.append("объектов: \n");
        for (Human human:humanList){
            sb.append(human);
            sb.append("\n");
        }
        return sb.toString();
    }
    @Override
public String toString(){
    return getInfo();
}
  @Override
    public Iterator<Human>iterator(){
        return new FamilyTreeIterator(humanList);
    }
    
    public void SortByName(){

        humanList.sort(new HumanComparatorByName());
    }


    public void SortByBirthDate(){humanList.sort(new HumanComparatorByBirthDate());}

}


