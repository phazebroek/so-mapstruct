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
        One one = new One(1, 10, 100, "one");
        boolean qualified = checkQualified(one, 10, 100, "one");
        boolean notQualified = checkQualified(one, 10, 100, "two");
        testMapperOneDtoIsQualified(one, qualified); // prints: id=[1], qualified=[true]
        testMapperOneDtoIsQualified(one, notQualified); // prints: id=[1], qualified=[false]
    }

    static void testMapperOneDtoIsQualified(One one, Boolean isQualified) {
        OneDto oneDto = mapper.toOneDto(one, isQualified);
        System.out.println("id=[" + oneDto.id + "], qualified=[" + oneDto.isQualified() + "]");
    }

    static boolean checkQualified(One one, int projId, int val, String code) {
        return one.getProjectId() == projId && one.getVal() == val && one.getCode().equalsIgnoreCase(code);
    }

    @Mapper
    public interface OneMapper {
        OneDto toOneDto(One one, Boolean qualified);
    }

    static class OneDto {

        private int id;
        private boolean qualified;

        public OneDto() {
        }

        public OneDto(int id, boolean qualified) {
            this.id = id;
            this.qualified = qualified;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        private int id;
        private int projectId;
        private int val;
        private String code;

        public One(int id, int projectId, int val, String code) {
            this.id = id;
            this.projectId = projectId;
            this.val = val;
            this.code = code;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProjectId() {
            return projectId;
        }

        public void setProjectId(int projectId) {
            this.projectId = projectId;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
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
