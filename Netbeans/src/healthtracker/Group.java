/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthtracker;

import java.util.List;

/**
 *
 * @author Noo
 */
public class Group {
    private String groupName;

    private List<User> members;

    public Group() {
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }
    
    public Group(String groupName,  List<User> members) {
        this.groupName = groupName;

        this.members = members;
    }

    /**
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }



    /**
     * @return the members
     */
    public List<User> getMembers() {
        return members;
    }

    /**
     * @param members the members to set
     */
    public void setMembers(List<User> members) {
        this.members = members;
    }
    public void addMember(User member)
    {
        this.members.add(member);
    }
    public void removeMember(User member)
    {
        this.members.remove(member);
        
    }
    
}
