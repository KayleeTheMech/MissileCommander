package events;

public class GameEventMetaData<T> {

  private final String contentDescription;

  private final T data;

  public GameEventMetaData(String contentDescription, T data) {
    this.data = data;
    this.contentDescription = contentDescription;
  }

  public String getContentDescription() {
    return contentDescription;
  }

  public T getData() {
    return data;
  }
}
