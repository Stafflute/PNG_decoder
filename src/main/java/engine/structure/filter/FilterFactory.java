package engine.structure.filter;

public class FilterFactory {
    public static Filter getFilter(byte filterType) {
        Filter result = null;

        if (filterType == NoneFilter.FILTER_TYPE) {
            result = new NoneFilter();
        } else if (filterType == SubFilter.FILTER_TYPE) {
            result = new SubFilter();
        } else if (filterType == UpFilter.FILTER_TYPE) {
            result = new UpFilter();
        } else if (filterType == AverageFilter.FILTER_TYPE) {
            result = new AverageFilter();
        } else if (filterType == PaethFilter.FILTER_TYPE) {
            result = new PaethFilter();
        }

        return result;
    }
}
