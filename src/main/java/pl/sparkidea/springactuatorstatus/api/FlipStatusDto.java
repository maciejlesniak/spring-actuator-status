package pl.sparkidea.springactuatorstatus.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.actuate.health.Status;

import java.util.Objects;

public class FlipStatusDto {

    @JsonProperty("status")
    private final Status status;

    @JsonCreator
    public FlipStatusDto(@JsonProperty("status") Status status) {
        this.status = status;
    }

    public String getStatus() {
        return status.getCode();
    }

    @Override
    public String toString() {
        return "FlipStatusDto{" +
                "status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlipStatusDto that)) return false;
        if (!super.equals(o)) return false;

        return Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}

