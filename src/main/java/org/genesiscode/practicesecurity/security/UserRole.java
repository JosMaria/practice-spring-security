package org.genesiscode.practicesecurity.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static org.genesiscode.practicesecurity.security.UserPermission.*;

public enum UserRole {

    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)),
    STUDENT(Sets.newHashSet());

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }
}
