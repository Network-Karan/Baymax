package com.company;/*
IMPORTANT NOTE:
PLEASE BE PATIENT WHEN INITIALLY RUNNING THE PROGRAM!
(It may take a moment or two after hitting "run" to load.)
*/

import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String args[])
    {
        //Setting up conversation (collecting basic user info)
        String userName = Conversation.ask("Hello! I'm your AI mental health chatbot.\nWhat's your name? (Type your name below.)");

        Conversation.formattedUser = " (" + userName.toUpperCase() + ")";

        Conversation.say("You have a nice name, " + userName + "!\nWhat's the story behind your name?");

        String chatbotName = Conversation.ask("Cool! Hey, I don't have a name yet.\nWhat would you like to call me?");

        Conversation.formattedChatbot = " (" + chatbotName.toUpperCase() + ")";

        Conversation.say(chatbotName + "...what an interesting name!\nHow did you come up with it?");

        Conversation.say("That's super cool! I'm glad you told me.");

        //Asking about mood
        String answer = Conversation.ask("So I'd like to know, " + userName + "...\nOn a scale from 1 (the worst) to 10 (the best), how are you feeling today?");

        double mood = 0;
        while (!(answer.equals("FISH!")))
            /*Here, I use the arbitrary "FISH!" instead of "" so the program doesn't continue forward if the user enters an empty string.*/
        {
            try
            {
                mood = Double.parseDouble(answer);
                answer = "FISH!";
            }
            catch (NumberFormatException | NullPointerException nfe)
            {
                answer = Conversation.ask("Whoops! Can't understand that.\nLet's try again...\nOn a scale from 1 to 10, how are you feeling?");
            }
        }

        boolean endConvo = false;
        if (mood > 7.5)
        {
            Conversation.say("That's awesome! What's making you feel this way?");
            Conversation.say("Glad to hear it!\nAnything else you want to tell me?");
            endConvo = true;
        }
        else if (mood > 5.0)
        {
            Conversation.say("Gotcha. What's making you feel this way?");
            Conversation.say("I see.\nAnything else you want to tell me?");
        }
        else
        {
            Conversation.say("Aw, I'm sorry! What's going on?");
            Conversation.say("That sounds rough.\nAnything else you want to tell me?");
        }

        if (endConvo)
        {
            //Conversation ends if user is already feeling good
            Conversation.say("Well, thanks for talking with me!\nYou really cheered me up.");
            Conversation.say("Bye, " + userName + "!");
        }
        else
        {
            //Conversation continues if user doesn't feel good
            boolean makesSense = false;
            String wantsRelax = Conversation.ask("I know a way to help you feel better.\nWould you like to try a positive visualization exercise?");
            wantsRelax = wantsRelax.toLowerCase();

            String[] good = new String[]{"yes", "yep", "yup", "okay", "sure", "yeah", "ok", "y"};
            List<String> goodAnswers = Arrays.asList(good);

            String[] bad = new String[]{"no", "nope", "no thanks", "nah", "not right now", "not now", "not really", "n"};
            List<String> badAnswers = Arrays.asList(bad);

            while (!makesSense)
            {
                if (goodAnswers.contains(wantsRelax))
                {
                    wantsRelax = "y";
                    makesSense = true;
                }
                else if (badAnswers.contains(wantsRelax))
                {
                    wantsRelax = "n";
                    makesSense = true;
                }
                else
                {
                    wantsRelax = Conversation.ask("Sorry, I don't understand that.\nTry giving a simple answer without any extra symbols.");
                    wantsRelax = wantsRelax.toLowerCase();
                }
            }

            if (wantsRelax.equals("n"))
            {
                Conversation.say("Okay, maybe some other time.\nAnyway, I'm glad we talked today.\nHow are you feeling now, " + userName + "?");
                Conversation.say("Good to know.\nWell, that's all for now...");
                Conversation.say("Bye, " + userName + "!");
            }
            else
            {
                Conversation.say("Great! Let's try this, " + userName + "!");
                Conversation.say("Imagine something that you think will happen in the future.\nIt could be something you're worried about or something you're looking forward to.");
                Conversation.say("What are you thinking of?");
                Conversation.say("Awesome!\nNow imagine yourself being successful in this situation.");
                Conversation.say("What does it look like? What are you saying or doing?");
                Conversation.say("Let yourself experience this situation for a moment.\nHow are you feeling right now?");
                Conversation.say("Excellent.\nDo you think this exercise has helped?");
                Conversation.say("Good to know.\nWell, I'm glad we talked today, " + userName + "!");
                Conversation.say("Bye!");
            }
        }
    }

    public static class Conversation
    {
        public static Scanner reader = new Scanner(System.in);
        public static String formattedUser = "";
        public static String formattedChatbot = "";
        private static String response;

        //Returns the user's response
        public static String ask(String text)
        {
            System.out.println("\nAI BUDDY" + formattedChatbot + ":\n" + text + "\n\nYOU" + formattedUser + ":");

            response = reader.nextLine();
            return response;
        }

        //"Listens" for user's response but returns nothing
        public static void say(String text)
        {
            System.out.println("\nAI BUDDY" + formattedChatbot + ":\n" + text + "\n\nYOU" + formattedUser + ":");

            response = reader.nextLine();
        }
    }
}