# Elevator Simulation 

**Author:** Pranav Tallapaka  

---

## Overview
This project simulates a **single elevator system** for a building with multiple floors.  
It is designed with object-oriented principles and direction-aware scheduling to mimic realistic elevator behavior.

---

## Features
- Handles multiple requests in order.
- Prioritizes requests in the **current direction** before reversing.
- Simulates motion, door actions, and time delays.
- Uses multi-threading to mimic real-time elevator operation.
- Modular, clean, and easy to extend.

---

## How It Works
1. Requests are added to **upQueue** or **downQueue** depending on floor position.  
2. The elevator serves all **up requests** before switching to **down requests**.  
3. Each floor takes **1 second** of travel time.  
4. Doors open and close with short simulated delays.

---

## Assumptions
- Building floors range from `0` to `10`.
- Only one elevator is simulated.
- Requests are integers representing floor numbers.
- The elevator cannot handle multiple passengers at once (simplified).

---

## Features Not Implemented
- Multi-elevator coordination.
- Time-based optimization or grouping of requests.
- Emergency stop or maintenance mode.

---
