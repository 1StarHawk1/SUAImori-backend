package com.suaimori.backend.model.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class SerializableRatingId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long user;
    private Long title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SerializableRatingId that = (SerializableRatingId) o;
        return Objects.equals(user, that.user) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, title);
    }
}
