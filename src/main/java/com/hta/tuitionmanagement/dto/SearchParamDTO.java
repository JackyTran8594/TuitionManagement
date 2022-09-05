package com.hta.tuitionmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchParamDTO<T> {
    public LocalDateTime fromDate;
    public LocalDateTime toDate;
    public T object;
    public String txtSearch;
    public Long id;
}
