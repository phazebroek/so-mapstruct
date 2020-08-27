package nl.phazebroek.so;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Example to demonstrate a mapstruct mapper with multiple source arguments to a single target.
 * This code complements an answer provided on StackOverflow: https://stackoverflow.com/a/52168618/1546042
 *
 * @author Pim Hazebroek
 */
@SuppressWarnings("ALL")
public class MapStructDemo {

    private static OneMapper mapper = Mappers.getMapper(OneMapper.class);

    /**
     * Runs the demo.
     */
    public static void main(String[] args) {
        One one = new One(1, 10, 100, "one");
        boolean qualified = checkQualified(one, 10, 100, "one");
        boolean notQualified = checkQualified(one, 10, 100, "two");
        testMapperOneDtoIsQualified(one, qualified); // prints: id=[1], qualified=[true]
        testMapperOneDtoIsQualified(one, notQualified); // prints: id=[1], qualified=[false]
    }

    static void testMapperOneDtoIsQualified(One one, Boolean isQualified) {
        OneDto oneDto = mapper.createOneDto(one, isQualified);
        System.out.println("id=[" + oneDto.id + "], qualified=[" + oneDto.isQualified() + "]");
    }

    /**
     * Checks if the given arguments are qualified for the given one. In order to be qualified, the projId, val and code must match with one
     * (ignoring case).
     *
     * @return true if the projId, val and code matches with the values of one, false otherwise.
     */
    static boolean checkQualified(One one, int projId, int val, String code) {
        return one.getProjectId() == projId && one.getVal() == val && one.getCode().equalsIgnoreCase(code);
    }

    @Mapper
    public interface OneMapper {

        /**
         * Create an instance of OneDto.
         * @param one the one.
         * @param qualified wether the OneDto is qualified or not.
         * @return the mapped OneDto instance.
         */
        OneDto createOneDto(One one, Boolean qualified);
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
