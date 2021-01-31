package com.ard333.springbootwebfluxjjwt.security.discord.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Data
@Setter(AccessLevel.PRIVATE)
public class Guild
{
    private String id;
    private String name;
    private String icon;
    private boolean owner;
    private Integer permissions;
    private List<String> features;

    public List<Permission> getPermissionList()
    {
        List<Permission> permissionList = new LinkedList<>();
        for (Permission permission : Permission.values())
        {
            if (permission.isIn(permissions))
            {
                permissionList.add(permission);
            }
        }
        return permissionList;
    }
}
