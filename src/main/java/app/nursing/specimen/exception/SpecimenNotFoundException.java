package app.nursing.specimen.exception;

public class SpecimenNotFoundException extends RuntimeException {

    public SpecimenNotFoundException(String nursingId) {
        super("해당 기록이 존재하지 않습니다: " + nursingId);
    }
}
