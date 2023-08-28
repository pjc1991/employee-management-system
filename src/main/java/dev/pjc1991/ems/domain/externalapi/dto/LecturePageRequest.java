package dev.pjc1991.ems.domain.externalapi.dto;

import dev.pjc1991.ems.dto.PageRequestImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class LecturePageRequest extends PageRequestImpl {
    /**
     * Creates a new {@link PageRequest} with sort parameters applied.
     *
     * @param pageNumber zero-based page number, must not be negative.
     * @param pageSize   the size of the page to be returned, must be greater than 0.
     * @param sort       must not be {@literal null}, use {@link Sort#unsorted()} instead.
     */
    protected LecturePageRequest(int pageNumber, int pageSize, Sort sort) {
        super(pageNumber, pageSize, sort);
    }
}
