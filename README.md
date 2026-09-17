# Cursed Kingdom Boss Fight Simulator

A text-based, turn-based Java simulator inspired by the Soulslike genre (Dark Souls, Elden Ring) — built for the SDD2 (Software Design and Development 2) portfolio module at the University of Huddersfield.

## Overview

You play as the Ashen Knight, fighting the Hollow King across repeated cycles. Each cycle, you choose an action — attack, defend, heal, upgrade your weapon, or rest at the shrine — while managing limited healing flasks and a souls-based currency. The boss grows stronger and enters a second phase once its health drops below 50%. Progress persists between sessions through save/load to a text file.

## OOP concepts demonstrated

- **Inheritance & encapsulation** — `Character` is the shared base class (name, health, attack power); `Player` and `Boss` extend it with their own behaviour, keeping shared state protected rather than duplicated.
- **Class interaction** — `Shrine` acts as an external progression system, operating on `Player` and `Boss` objects rather than owning their state directly.
- **State machines** — the boss transitions into a permanent, stronger "Phase Two" once a health threshold is crossed.
- **File I/O / persistence** — the full game state (cycle, health, weapon level, boss phase, flasks, souls, deaths) is written to and read back from `boss_save.txt`.

## Classes

| Class | Responsibility |
| --- | --- |
| `Main` | Menu loop, simulation loop, save/load orchestration |
| `Character` | Shared base class for anything that can take damage and die |
| `Player` | Attacks, healing, weapon upgrades, defending |
| `Boss` | Attack pattern, phase-two transition |
| `Shrine` | Flasks, souls currency, weapon upgrade cost, death penalty, resting |

## Running it

```bash
javac *.java
java Main
```

## Credits

Inspired by *Dark Souls* (FromSoftware, 2011) and *Elden Ring* (FromSoftware, 2022) — adapted here into a simplified, original text-based simulation for educational purposes.
