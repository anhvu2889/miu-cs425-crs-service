package miu.cs425.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DynamicFilterSortRequest {

    private Integer page;

    private Integer pageSize;

    private List<SortItem> sortModels;

    private FilterModel filterModel;

    @Setter
    @Getter
    public static class SortItem {
        private String field;
        private String sort;
    }

    @Setter
    @Getter
    public static class FilterModel {
        private List<FilterItem> items;
    }

    @Setter
    @Getter
    public static class FilterItem {
        private Integer id;
        private String field;
        private Object value;
        private String operator;
    }
}
