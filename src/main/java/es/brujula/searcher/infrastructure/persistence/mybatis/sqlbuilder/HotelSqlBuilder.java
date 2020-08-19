package es.brujula.searcher.infrastructure.persistence.mybatis.sqlbuilder;

import es.brujula.searcher.application.query.PaginatedSearchParam;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class HotelSqlBuilder {

    private static final String TABLE_HOTEL = "T_SPP_HOTEL";

    private static final String COLUMN_ID = "HOT_PRVCODIGO";
    private static final String COLUMN_NAME = "HOT_NOMBRE";
    private static final String COLUMN_GMS = "HOT_CODIGO";
    private static final String COLUMN_TOTAL = "HOT_TPLAZAS";
    private static final String COLUMN_OFFERED = "HOT_OFTPLZS";
    private static final String COLUMN_SEASON = "HOT_TEMPORADA";
    private static final String COLUMN_PROVIDER_NAME = "HOT_PRVNOMBRE";
    private static final String COLUMN_PROVIDER_DOCUMENT_IDENTIFIER = "HOT_PRVCIF";
    private static final String COLUMN_CATEGORY = "HOT_CATEGORIA";
    private static final String COLUMN_PROVINCE = "HOT_PRNCODIGO";
    private static final String COLUMN_CITY = "HOT_MUNCODIGO";

    private static final String FILTER_BY_NAME = "name";
    private static final String FILTER_BY_GMS = "gmsCode";
    private static final String FILTER_BY_SEASON = "season";
    private static final String FILTER_BY_TOTAL_PLACES = "total";
    private static final String FILTER_BY_OFFERED_PLACES = "offered";

    private static final String ORDER_BY_ID_ASC = "id";
    private static final String ORDER_BY_ID_DESC = "-id";
    private static final String ORDER_BY_NAME_ASC = "name";
    private static final String ORDER_BY_NAME_DESC = "-name";
    private static final String ORDER_BY_GMS_ASC = "gmsCode";
    private static final String ORDER_BY_GMS_DESC = "-gmsCode";
    private static final String ORDER_BY_SEASON_ASC = "season";
    private static final String ORDER_BY_SEASON_DESC = "-season";
    private static final String ORDER_BY_TOTAL_PLACES_ASC = "total";
    private static final String ORDER_BY_TOTAL_PLACES_DESC = "-total";
    private static final String ORDER_BY_OFFERED_PLACES_ASC = "offered";
    private static final String ORDER_BY_OFFERED_PLACES_DESC = "-offered";

    private static final String ORDER_ASC = " ASC";
    private static final String ORDER_DESC = " DESC";

    public String search(PaginatedSearchParam searchParam) {

        SQL sql = this.queryFromHotel();

        this.applyFilters(searchParam.filters(), sql);
        this.applySorters(searchParam.sort(), sql);

        sql.OFFSET(searchParam.page());
        sql.LIMIT(searchParam.limit());

        return sql.toString();
    }

    public String count(PaginatedSearchParam searchParam) {

        SQL sql = new SQL()
                .SELECT("COUNT(" + COLUMN_ID + ")")
                .FROM(TABLE_HOTEL);

        this.applyFilters(searchParam.filters(), sql);

        return sql.toString();
    }

    private void applyFilters(Map<String, String> filters, SQL sql) {

        for (Map.Entry<String, String> entry : filters.entrySet()) {
            String filterKey = entry.getKey();
            String filterValue = filters.get(filterKey);

            switch (filterKey) {
                case FILTER_BY_NAME:
                    sql.WHERE(COLUMN_NAME + "='" + filterValue + "'");
                    break;
                case FILTER_BY_SEASON:
                    sql.WHERE(COLUMN_SEASON + "='" + filterValue + "'");
                    break;
                case FILTER_BY_GMS:
                    sql.WHERE(COLUMN_GMS + "='" + filterValue + "'");
                    break;
                case FILTER_BY_TOTAL_PLACES:
                    sql.WHERE(COLUMN_TOTAL + ">='" + filterValue + "'");
                    break;
                case FILTER_BY_OFFERED_PLACES:
                    sql.WHERE(COLUMN_OFFERED + ">='" + filterValue + "'");
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    private void applySorters(String orderBy, SQL sql) {

        switch (orderBy) {
            case ORDER_BY_ID_ASC:
                sql.ORDER_BY(COLUMN_ID.concat(ORDER_ASC));
                break;
            case ORDER_BY_ID_DESC:
                sql.ORDER_BY(COLUMN_ID.concat(ORDER_DESC));
                break;
            case ORDER_BY_NAME_ASC:
                sql.ORDER_BY(COLUMN_NAME.concat(ORDER_ASC));
                break;
            case ORDER_BY_NAME_DESC:
                sql.ORDER_BY(COLUMN_NAME.concat(ORDER_DESC));
                break;
            case ORDER_BY_GMS_ASC:
                sql.ORDER_BY(COLUMN_GMS.concat(ORDER_ASC));
                break;
            case ORDER_BY_GMS_DESC:
                sql.ORDER_BY(COLUMN_GMS.concat(ORDER_DESC));
                break;
            case ORDER_BY_SEASON_ASC:
                sql.ORDER_BY(COLUMN_SEASON.concat(ORDER_ASC));
                break;
            case ORDER_BY_SEASON_DESC:
                sql.ORDER_BY(COLUMN_SEASON.concat(ORDER_DESC));
                break;
            case ORDER_BY_TOTAL_PLACES_ASC:
                sql.ORDER_BY(COLUMN_TOTAL.concat(ORDER_ASC));
                break;
            case ORDER_BY_TOTAL_PLACES_DESC:
                sql.ORDER_BY(COLUMN_TOTAL.concat(ORDER_DESC));
                break;
            case ORDER_BY_OFFERED_PLACES_ASC:
                sql.ORDER_BY(COLUMN_OFFERED.concat(ORDER_ASC));
                break;
            case ORDER_BY_OFFERED_PLACES_DESC:
                sql.ORDER_BY(COLUMN_OFFERED.concat(ORDER_DESC));
                break;
            default:
                throw new IllegalArgumentException();

        }
    }

    public String byId(Long id) {

        SQL sql = this.queryFromHotel()
                .WHERE(COLUMN_ID + "=" + id)
                .LIMIT(1);

        return sql.toString();
    }

    private SQL queryFromHotel() {
        return new SQL()
                .SELECT(COLUMN_ID, COLUMN_NAME, COLUMN_GMS, COLUMN_SEASON, COLUMN_PROVIDER_NAME,
                        COLUMN_PROVIDER_DOCUMENT_IDENTIFIER, COLUMN_CATEGORY, COLUMN_PROVINCE, COLUMN_CITY,
                        COLUMN_TOTAL, COLUMN_OFFERED)
                .FROM(TABLE_HOTEL);
    }
}
