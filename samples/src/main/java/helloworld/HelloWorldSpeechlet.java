/**
 Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with the License. A copy of the License is located at

 http://aws.amazon.com/apache2.0/

 or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package helloworld;

import java.util.Map;
import java.util.*;
import java.io.IOException;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.eclipsesource.json.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;

/**
 * This sample shows how to create a simple speechlet for handling speechlet requests.
 *
 List
    Add
    Remove
    Open
        Add List Object to Session
        "What would you like to do with this list?"

 Task
    Get Task
        Search by Tasks
    Complete Task
    Create Task
        If List
            Add to List
        Else
            Add to Inbox
    Update Task

 */
//
public class HelloWorldSpeechlet implements Speechlet {
    private static final Logger log = LoggerFactory.getLogger(HelloWorldSpeechlet.class);

    private static final String LIST_KEY = "LIST";
    private static final String LIST_SLOT = "event";
    private static final String INBOX_KEY = "inbox";

    @Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session)
            throws SpeechletException {
        session.setAttribute(LIST_KEY, INBOX_KEY);


        log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        // any initialization logic goes here
    }

    @Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session)
            throws SpeechletException {
        log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        return getWelcomeResponse();
    }

    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session)
            throws SpeechletException {
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
//        Wund item = new Wund(session.getUser().getAccessToken());
        String[] intentValues = new String[4];
        String[] vals = new String[2];
        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;
        String[] intentNames = {"event", "date", "timeStart", "duration"};

        if ("OpenListIntent".equals(intentName)) {
            System.out.println();
            Slot slot = intent.getSlot("event");
            if (slot != null && slot.getValue() != null) {

                vals = new String[]{"I have now opened the list named ", "What would you like to do next in the list?"};
                return setListInSession(true, vals, "open", intent, session);
            } else {
                SimpleCard card = new SimpleCard();
                card.setTitle("Session");
                //card.setContent(speechText);
                card.setContent(slot.getValue());

                // Create the plain text output.
                PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
                speech.setText("I'm not quite sure what list you were looking for");
                return SpeechletResponse.newTellResponse(speech, card);
//                String speechText =
//                        "";
//                String repromptText =
//                        "Please try again. ";
//
//                return getSpeechletResponse(speechText, repromptText, true, session);

            }
        }
          else if ("CreateListIntent".equals(intentName)) {
            System.out.println();

            Slot slot = intent.getSlot("event");
            if (slot != null && slot.getValue() != null) {
                //if ben's code returns false, dont set session
                //return getSpeechletResponse("I'm unable to add a list now. Please try again later", "", false, session);
                //otherwise, set session to the name of the event
                //
                vals = new String[]{"I have now created the list named ", "What would you like to do next in the list?"};
                try {
                    new Wund(session.getUser().getAccessToken()).addList(intent.getSlot("event").getValue());
                } catch(Exception e){
                    vals[0] = "Failure";
                }

                return setListInSession(true, vals, "create", intent, session);

            } else {
                String speechText =
                        "I couldn't hear what the list name was.";
                String repromptText =
                        "Please try again. ";

                return getSpeechletResponse(speechText, repromptText, true, session);

            }
        }
        else if ("DeleteListIntent".equals(intentName)) {
            System.out.println();

            Slot slot = intent.getSlot("event");
            if (slot != null && slot.getValue() != null && slot.getValue() != INBOX_KEY) {
                //if ben's code returns false, dont set session
                //return getSpeechletResponse("I'm unable to remove a list now. Please try again later", "", false, session);
                //otherwise, set session to the name of the event
                //
                if(slot.getValue().equals(session.getAttribute(LIST_KEY))){
                    session.setAttribute(LIST_KEY, INBOX_KEY);
                }
                vals = new String[]{"I have now deleted the list named ","What would you like to do next?"};
                return setListInSession(false, vals, "delete",intent, session);


            }
            else{
                String speechText =
                        "I couldn't hear what the list name was.";
                String repromptText =
                        "Please try again. ";

                return getSpeechletResponse(speechText, repromptText, true, session);

            }
        }
        else if ("CreateEventIntent".equals(intentName)) {
            String empt = "";

            for(int i = 0; i < 4; i++)
            {
                Slot slot = intent.getSlot(intentNames[i]);
                if(slot != null && slot.getValue() != null) {
                    intentValues[i] = slot.getValue().toString();
                }
                else
                    intentValues[i] = "";
            }
//            if(intent.getSlot("event") != null){
//                try {
//                    new Wund(session.getUser().getAccessToken()).createTask(session.getAttribute(LIST_KEY).toString(), intent.getSlot("event").getValue(), null, 1);
//                } catch (Exception e){
//                    return getCreationResponse(new String[]{"Failure"});
//                }
//            }
//            String s = "";
//            for(int i = 0; i < intentValues.length; i++){
//                s += " "  + intentValues[i];
//            }
//            Slot slot = intent.getSlot("event"); return getSpeechletResponse(slot.getValue(), "", true, session);
            return getCreationResponse(new String[]{""});
        }
        else if("ViewEventIntent".equals(intentName))
        {
            String empt = "";

            for(String in:intentNames)
            {
                Slot slot = intent.getSlot(in);
                if(slot != null && slot.getValue() != null) {
                    empt += in +"::" +slot.getValue().toString() + "\r\n";
                }
            }

            if(intent.getSlot("event") == null){
                List<WTask> l = new Wund(session.getUser().getAccessToken()).getAllTasks(session.getAttribute(LIST_KEY).getValue());
                if(l == null){
                    return getViewResponse("Failure");
                } else {
                    String items = "";
                    int i = 0;
                    for(WTask w : l){
                        items += w.getTitle();
                       i++;
                    }
                    return getViewResponse(items);
                }
            }

            return getViewResponse(empt);
        }
        else if ("AMAZON.HelpIntent".equals(intentName)) {
            return getHelpResponse();
        } else {
            throw new SpeechletException("Invalid Intent");
        }


    }
    private SpeechletResponse setListInSession(boolean change, String pass[], String fail, final Intent intent, final Session session) {
        // Get the slots from the intent.
        Map<String, Slot> slots = intent.getSlots();

        // Get the color slot from the list of slots.
        Slot favoriteListSlot = slots.get(LIST_SLOT);
        String speechText, repromptText;

        // Check for favorite color and create output to user.
        if (favoriteListSlot != null) {
            // Store the user's favorite color in the Session and create response.
            String listname = favoriteListSlot.getValue();
            if(change)
                session.setAttribute(LIST_KEY, listname);
            speechText =
                    String.format(pass[0] + listname);
            repromptText = pass[1];

        } else {
            // Render an error since we don't know what the users favorite color is.
            speechText = "I'm not sure what list you are trying to " + fail;
            repromptText ="Please try again. ";
        }

        return getSpeechletResponse(speechText, repromptText, true, session);
    }
    /**
     * Returns a Speechlet response for a speech and reprompt text.
     */
    private SpeechletResponse getSpeechletResponse(String speechText, String repromptText,
                                                   boolean isAskResponse,Session session) {
        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle(speechText);
        //card.setContent(speechText);
        card.setContent(session.getAttribute(LIST_KEY).toString());

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        if (isAskResponse) {
            // Create reprompt
            PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
            repromptSpeech.setText(repromptText);
            Reprompt reprompt = new Reprompt();
            reprompt.setOutputSpeech(repromptSpeech);
            return SpeechletResponse.newAskResponse(speech, reprompt, card);

        } else {

            return SpeechletResponse.newTellResponse(speech, card);
        }
    }
    @Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session)
            throws SpeechletException {
        log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        // any cleanup logic goes here
    }

    /**
     * Creates and returns a {@code SpeechletResponse} with a welcome message.
     *
     * @return SpeechletResponse spoken and visual response for the given intent
     */
    private SpeechletResponse getWelcomeResponse() {
        String speechText = "I have opened Wunderlist ";
        //
        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("HelloWorld");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    /**
     * Creates a {@code SpeechletResponse} for the hello intent.
     *
     * @return SpeechletResponse spoken and visual response for the given intent
     */
    private SpeechletResponse getCreationResponse(String[] item) {
        String speechText = item[0];

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Create Intent");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }
    private SpeechletResponse getViewResponse(String item) {
        String speechText = item;

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("View Intent");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }

    private SpeechletResponse getHelloResponse() {
        String speechText = "Hello world";

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("HelloWorld");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }

    /**
     * Creates a {@code SpeechletResponse} for the help intent.
     *
     * @return SpeechletResponse spoken and visual response for the given intent
     */
    private SpeechletResponse getHelpResponse() {
        String speechText = "You can say hello to me!";

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("HelloWorld");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }
}
