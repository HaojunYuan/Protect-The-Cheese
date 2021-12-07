# Run the application
### 1. Clone
git clone https://github.com/HaojunYuan/Protect-The-Cheese.git
### 2. Open the project with gradle in intellij
### 3. Go to gradle config click "run" under application task

# Troubleshooting
1. File -> Invalidate Caches/Restart
2. Click "Invalidate and Restart"
3. Wait for download and indexing the project
4. Go to gradle config click "run" under application task

# Test Deliverable (tests->controller->M6Tests)
The game design for Boss is that the boss cannot be defeated by the player. The player will feel sad and disappointed, however, when the boss reaches the monument, the boss regrets what he had done, and decided to be a good monster, and decided not to attack the monument. We want to surprise the player so that they enjoy the happiness.

For M6Tests, we did the following tests:

1. Confirm that different difficulty levels should have different money and health
2. Confirm that different towers should have different costs
3. Confirm that player can upgrade the tower by clicking on the bought tower to upgrade it
4. Confirm that if monument health<0 then lose screen should appear
5. Confirm that if the final boss reaches the monument, the player just wins the game
6. Lose screen and win screen should have different statistics
7. Confirm that if a monster gets defeated, the money should increase
8. Confirm that if the monument is attacked, the monument's health should decrease.
