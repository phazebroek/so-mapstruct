package nl.phazebroek.so;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Pim Hazebroek
 */
@SuppressWarnings("ALL")
public class MapStructDemo {

    private static OneMapper mapper = Mappers.getMapper(OneMapper.class);

    public static void main(String[] args) {
        var one = new One(1, 10, 100, "one");
        var qualified = checkQualified(one, 10, 100, "one");
        var notQualified = checkQualified(one, 10, 100, "two");
        testMapperOneDtoIsQualified(one, qualified);
        testMapperOneDtoIsQualified(one, notQualified);
    }

    static void testMapperOneDtoIsQualified(One one, Boolean isQualified) {
        var oneDto = mapper.toOneDto(one, isQualified);
        System.out.println("id=[" + oneDto.id + "], qualified=[" + oneDto.isQualified() + "]");
    }

    static Boolean checkQualified(One one, Integer projId, Integer val, String code) {
        return one.getProjectId().equals(projId) && one.getVal().equals(val) && one.getCode().equalsIgnoreCase(code);
    }

    @Mapper
    interface OneMapper {
        OneDto toOneDto(One one, Boolean qualified);
    }

    static class OneDto {

        private Integer id;
        private boolean qualified;

        public OneDto() {
        }

        public OneDto(Integer id, boolean qualified) {
            this.id = id;
            this.qualified = qualified;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public boolean isQualified() {
            return qualified;
        }

        public void setQualified(boolean qualified) {
            this.qualified = qualified;
        }
    }

    static class One {

        private Integer id;
        private Integer projectId;
        private Integer val;
        private String code;

        public One(Integer id, Integer projectId, Integer val, String code) {
            this.id = id;
            this.projectId = projectId;
            this.val = val;
            this.code = code;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getProjectId() {
            return projectId;
        }

        public void setProjectId(Integer projectId) {
            this.projectId = projectId;
        }

        public Integer getVal() {
            return val;
        }

        public void setVal(Integer val) {
            this.val = val;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

}
