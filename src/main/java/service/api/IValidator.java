package service.api;

public interface IValidator<T> {

    boolean validate(T data);
}
