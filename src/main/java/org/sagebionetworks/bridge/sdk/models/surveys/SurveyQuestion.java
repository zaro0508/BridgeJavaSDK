package org.sagebionetworks.bridge.sdk.models.surveys;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.List;
import java.util.Objects;

import org.joda.time.DateTime;
import org.sagebionetworks.bridge.sdk.models.holders.GuidHolder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(as=SurveyQuestion.class)
public final class SurveyQuestion implements SurveyElement, GuidHolder {

    private String guid;
    private String identifier;
    private String type;
    private String prompt;
    private UiHint hint;
    private Constraints constraints;

    public SurveyQuestion() {
        setType("SurveyQuestion");
    }
    @Override
    public String getGuid() {
        return guid;
    }
    public void setGuid(String guid) {
        this.guid = guid;
    }
    public String getIdentifier() {
        return identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getPrompt() {
        return prompt;
    }
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
    @JsonSerialize(using = EnumSerializer.class)
    @JsonProperty("uiHint")
    public UiHint getUIHint() {
        return hint;
    }
    @JsonDeserialize(using = UiHintDeserializer.class)
    public void setUiHint(UiHint hint) {
        this.hint = hint;
    }
    public Constraints getConstraints() {
        return constraints;
    }
    @JsonDeserialize(using = ConstraintsDeserializer.class)
    public void setConstraints(Constraints constraints) {
        this.constraints = constraints;
    }
    @JsonIgnore
    public SurveyAnswer declineAnswerForQuestion(String client) {
        checkArgument(isNotBlank(client), "Client string must indicate client (e.g. mobile or desktop)");
        SurveyAnswer surveyAnswer = new SurveyAnswer();
        surveyAnswer.setQuestionGuid(getGuid());
        surveyAnswer.setAnsweredOn(DateTime.now());
        surveyAnswer.setDeclined(true);
        surveyAnswer.setClient(client);
        return surveyAnswer;
    }
    @JsonIgnore
    public SurveyAnswer createAnswerForQuestion(String answer, String client) {
        checkArgument(isNotBlank(client), "Client string must indicate client (e.g. mobile or desktop)");
        SurveyAnswer surveyAnswer = new SurveyAnswer();
        surveyAnswer.setQuestionGuid(getGuid());
        surveyAnswer.setAnsweredOn(DateTime.now());
        surveyAnswer.getAnswers().add(answer);
        surveyAnswer.setClient(client);
        return surveyAnswer;
    }
    @JsonIgnore
    public SurveyAnswer createAnswerForQuestion(List<String> answers, String client) {
        checkArgument(isNotBlank(client), "Client string must indicate client (e.g. mobile or desktop)");
        SurveyAnswer surveyAnswer = new SurveyAnswer();
        surveyAnswer.setQuestionGuid(getGuid());
        surveyAnswer.setAnsweredOn(DateTime.now());
        surveyAnswer.setAnswers(answers);
        surveyAnswer.setClient(client);
        return surveyAnswer;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((constraints == null) ? 0 : constraints.hashCode());
        result = prime * result + ((getGuid() == null) ? 0 : getGuid().hashCode());
        result = prime * result + ((hint == null) ? 0 : hint.hashCode());
        result = prime * result + ((getIdentifier() == null) ? 0 : getIdentifier().hashCode());
        result = prime * result + ((prompt == null) ? 0 : prompt.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SurveyQuestion other = (SurveyQuestion) obj;
        return (Objects.equals(constraints, other.constraints) && Objects.equals(guid, other.guid)
                && Objects.equals(hint, other.hint) && Objects.equals(identifier, other.identifier)
                && Objects.equals(prompt, other.prompt) && Objects.equals(type, other.type));
    }
    @Override
    public String toString() {
        return "SurveyQuestion [guid=" + getGuid() + ", identifier=" + getIdentifier() + ", prompt=" + prompt
                + ", hint=" + hint + ", constraints=" + constraints + "]";
    }

}
