public class Subtask extends Task {
    private int EpicId;

    public Subtask(String name, String desc) {
        super(name, desc);
    }

    public Subtask(String name, String desc, int EpicId) {
        super(name, desc);

        this.EpicId = EpicId;
    }

    public int getEpicId() {
        return EpicId;
    }

    public void setEpicId(int epicId) {
        EpicId = epicId;
    }
}
