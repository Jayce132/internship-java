package ro.nymphis.dto.pagination;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class PaginatedResponseDTO<T> {

    private Integer currentPage;
    private Integer totalPages;
    private Long totalEntries;
    Collection<T> responseDTOs;
}
