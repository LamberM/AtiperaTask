package org.lamberm.AtiperaTask.github.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class BranchDetailsResponse {
    private String name;

    private Commit commit;
}
