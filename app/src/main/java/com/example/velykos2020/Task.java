package com.example.velykos2020;

public class Task {
    private final Kid kid;
    private final String question;
    private final String answer;

    public Task(Kid kid, String question, String answer) {
        this.kid = kid;
        this.question = question;
        this.answer = answer;
    }

    public Kid getKid() {
        return kid;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public enum Kid {
        VISI,
        BENAS,
        MILDA,
        SIMAS
    }
}
