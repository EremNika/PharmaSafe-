package pharma.model;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class BaseEntity {
    protected String id;
    protected LocalDateTime createdAt;

    public BaseEntity(String id) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
    }

    public String getId(){
        return id;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setId(String id){
        this.id=id;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }

    @Override
    public boolean equals(Object obj){
        if(this==obj)return true;
        if(obj==null || getClass()!=obj.getClass())return false;
        BaseEntity be=(BaseEntity) obj;
        return Objects.equals(id, be.id) && Objects.equals(createdAt, be.createdAt);
    }
    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }
    // TODO: занятие 1 - добавить геттеры/сеттеры, equals, hashCode
}

