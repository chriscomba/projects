# My Personal Project

## Workout Tracker
The Workout Tracker Application is designed to help athletes, fitness enthusiasts, and casual exercisers monitor their workouts, track progress, and achieve their fitness goals with personalized insights and recommendations. It provides users with a comprehensive toolset to not only log their daily workouts but also analyze performance trends and optimize their routines over time.

Key Features:
Workout Logging:

Users can log exercises based on various categories such as cardio, strength training, flexibility, or custom workouts. Each workout entry will allow users to specify details such as sets, repetitions, weights, and duration, ensuring comprehensive workout tracking.
Exercise Categorization by Muscle Group:

The application will categorize exercises based on the targeted muscle group (e.g., chest, back, legs, core) and allow users to filter exercises to create balanced workout routines. This feature helps users focus on specific areas of their fitness plan and ensures that all muscle groups are trained effectively.
Progress Tracking Over Time:

The application will visualize progress over time using graphs and charts to display key metrics such as strength gains, endurance improvements, weight lifted, or time spent on specific activities. Users will be able to compare past workouts, observe trends, and measure their improvement against set goals.
Personalized Recommendations:

Based on the user’s workout history and activity patterns, the application will provide personalized workout suggestions. Whether a user is plateauing or looking for variety, the app will recommend new routines, exercises, or adjustments to existing plans to enhance progress. Recommendations will consider factors such as recovery time, muscle group balance, and user preferences.
Goal Setting and Achievement:

Users will be able to set short-term and long-term fitness goals (e.g., increase squat weight, run a 5K under a specific time). The application will help track progress toward these goals and provide reminders or motivational insights to keep users on track.
Routine Customization and Templates:

Users can build their own routines or choose from pre-designed templates that focus on specific fitness goals, such as strength building, fat loss, or endurance. These templates will be customizable, and users can save their favorite routines for future use.
Historical Data and Trends:

The app will store all past workout data, allowing users to review their performance at any time. Advanced filters will enable users to explore specific time periods or performance on particular exercises, giving them deep insights into their progress.
User-Friendly Interface and Mobile Support:

Designed with simplicity and efficiency in mind, the application will feature a clean and intuitive interface that makes logging workouts fast and easy. Mobile-friendly, the app will allow users to log workouts on the go, ensuring that no session is missed.
Target Audience:
The Workout Tracker Application is tailored for anyone passionate about fitness—whether they are professional athletes, bodybuilders, runners, or casual gym-goers. The app is flexible enough to accommodate a wide range of fitness routines and user goals, from muscle building and weight loss to endurance training and rehabilitation.


- It will categorize exercises by muscle group
- Track progress over time
- Recommend routines based on past activity

This project will be used by any athlete or fitness enthusiast who wants to track their progress and work towards a fitness goal

My source of inspiration for this project is my own personal fitness journey in the gym. At the initial stages i found my progress to be quite
slow and using a tracker would be able to speed up my progress. This project allows me to work with structuring data (exercises, workouts, progress) while also offering a way to build something practical for personal use or to help others in their fitness journey.

- As a user, I want to be able to log a new workout session so that I can track my exercise routine.

- As a user, I want to be able to view a history of my logged workouts so that I can review past progress.

- As a user, I want to categorize exercises by muscle group (e.g., chest, back, legs) so that I can create balanced workout plans.

- As a user, I want to be able to see the remaining excercises left to do

- As a user, I want to get suggestions for excercises to add to my workout

- As a user, I want to be able to save my workouts left to do

- As a user, I want to be able to load my workout left to do

## Instructions for End user

How to add an Exercise to the Gym

- To add a new exercise, fill in the fields labeled "Exercise Name," "Category," and "Difficulty" in the top input section of the GUI.  The exercise will be added to your list of exercises.

- You can generate the first required action by entering multiple exercises into the input fields and clicking the "Add Exercise" button for each one.

- You can generate the second required action by selecting an exercise from the panel and clicking the log workout button. This will add the exercise to the workout history and remove it from exercises.


- The visual component, a pie chart representing the exercise categories, can be accessed by clicking the "Show Pie Chart" button in the bottom button panel of the GUI. The chart will open in a new window showing a pie chart of the history of excercises.

- Click the "Save Gym" button. This will save the current state of all exercises and logged workouts 

- Click the "Load Gym" button. This will load the saved exercises and logged workouts.

Phase 4: Task 2

Tue Nov 26 23:23:14 PST 2024
Push-Ups has been added to exercises
Tue Nov 26 23:23:22 PST 2024
Deadlift has been added to exercises
Tue Nov 26 23:23:29 PST 2024
Push-Ups has been logged
Tue Nov 26 23:23:29 PST 2024
Push-Ups has been added to workout history


Phase 4 Task 3:

Enhance the Relationship between Gym and Excercise as currently Gym directly holds multiple collections of Excercise objects (e.g., excercises, workoutHistory). This could be refactored into a dedicated class such as WorkoutPlan which encapsulates the relationships and behaviors related to a collection of exercises. This would reduce the responsibility of the Gym class and promote single responsibility.
