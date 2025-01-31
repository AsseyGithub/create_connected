# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## 0.6.1 - 2024-01-24

### Fixed

- Incorrect item drops with custom loot tables when a Copycat Board is sneak-wrenched
- Various cases where copycats are not being treated the same as their real counterparts:
    - Copycat Walls not able to pave tracks
    - Incorrect placement of Copycat Fences when placed in a filter slot
    - Incorrect pathfinding around Copycat Fence Gates
    - Incorrect position when a passenger on a seat is being ejected due to collision with Copycat Slabs
    - Incorrect transforms when Copycat Stairs are disassembled from a rotated contraption
- Translation fixes in the ponder scene for Linked Transmitter

## 0.6.0 - 2024-01-24

### Added

- **Copycat Stairs**
    - Connected textures may not be perfect due to copycat limitations
- **Copycat Fence**
- **Copycat Fence Gate**
- **Copycat Wall**
- **Copycat Board**
    - Pre-assembled versions: Copycat Box and Copycat Catwalk
- Lock mechanism for linked transmitters to avoid misclicks on the frequency slots

### Fixed

- Crash due to bad MixinExtras version (0.3.3) (#24, #26)
- Crash due to concurrent modification when registering block stress values (#13)
- Copycat Vertical Step not mirroring properly in schematics
- Custom copycats not having their block entities registered
- Linked Analog Lever not responding to sneak-right click when holding item in hand (#25)
- Missing registrations for Linked Stone Buttons in 1.18/1.19 (#25)
- Copycat Slabs connecting their textures across a half-block gap (#27)
- Small error in the ponder scene for the Linked Transmitter

## 0.5.1 - 2024-01-19

### Fixed

- Instant schematic upload not working for schematics in sub-folders

## 0.5.0 - 2024-01-19

### Added

- **Support for schematics in sub-folders**
    - The Schematic Table now searches for schematics in sub-folders
    - Schematics maintain their folder structure when uploaded to servers
    - The Schematic and Quill now saves schematics in sub-folders if their names contain `/` or ` \ `
- **Redstone support for smart clutches**
    - The Overstress Clutch, Centrifugal Clutch and Freewheel Clutch now give comparator signal outputs when they are
      coupled
    - The Overstress Clutch can now be reset with a redstone pulse
- **Cherry window and window pane** (#18)
- **Bamboo window and window pane** (#18)
- **New "Loop" instruction for Sequenced Gearshift**
    - Restart the whole sequence from the beginning, allowing the Sequenced Gearshift to run indefinitely
- A new toggle to disable "crafting remaining item" fix for manual application recipes
- Translations for Russian, Japanese, Dutch, Chinese Simplified, Romanian
  (thanks [Crowdin contributors](https://crowdin.com/project/create-connected-mod/members)!)

### Changed

- The block ID of modded linked buttons now include mod ID

### Fixed

- Incompatibility with mods that register new `BlockSetType`s with their mod IDs (#19)
- Vertical brass gearbox returning incorrect drops (#21)
- Connected textures on Copycat Slabs not considering diagonal connections (#20)
- Connected textures on Copycat Slabs break connection on one side if there is a height difference on the other side
- Speed modifiers not working for the "turn for time" instruction in Sequenced Gearshift

## 0.4.2 - 2024-01-12

### Fixed

- Crash due to unsafe early block entry access (#16)
- Jukebox on contraption not stopping after contraption is destroyed

## 0.4.1 - 2024-01-11

### Fixed

- Crash due to incorrect remapping

## 0.4.0 - 2024-01-11

### Added

- **Linked Transmitter**
    - For all button variants
    - For Lever
    - For Linked Lever
- **Contraption support for vanilla blocks**
    - Note Block
        - Can be tuned on contraptions
        - Plays automatically when on trains/elevators
        - Supports contraption controls
    - Jukebox
        - Can swap discs on contraptions
        - Plays/stops automatically when on trains/elevators
        - Supports contraption controls
    - Crafting Table
    - Stonecutter
    - Grindstone
    - Smithing Table
    - Loom
    - Cartography Table
- 2 music discs for iconic elevator music
- Translations for Dutch, Japanese, Chinese Simplified, French, German
  (thanks [Crowdin contributors](https://crowdin.com/project/create-connected-mod/members)!)

### Fixed

- Water in Fan Washing Catalyst not transparent
- Copycat Slabs not dropping 2 items when a double variant is broken (#14)

## 0.3.1 - 2023-12-29

### Fixed

- Freewheel clutch/centrifugal clutch not updating on speed change (#9)

## 0.3.0 - 2023-12-29

### Added

- **Sequenced Pulse Generator**
- Control Chip - a crafting ingredient for the Sequenced Pulse Generator
- Ponders for
    - Encased Chain Cogwheel
    - Inverted Clutch
    - Inverted Gearshift
    - Parallel Gearbox
    - Sequenced Pulse Generator

### Fixed

- Z-fighting on the model of Encased Chain Cogwheel
- Crash when JEI is not installed (#7)

## 0.2.0 - 2023-12-25

Merry Christmas!

### Added

- **Stress value config for all kinetic components** (#4) - a server config system similar to base Create
- Separate stress value config for powered/unpowered brakes
- Item Silo
- Simplified Chinese translation (thanks @xiewuzhiying) (#6)

### Fixed

- Clutch variants incorrectly suppressing kinetic network updates
- Mod incompatibility due to item attributes being accessed too early in initialization (#5)

## 0.1.0 - 2023-12-23

### Added

- **Support for Minecraft 1.19.2 and 1.18.2** (#1)
- Dutch translation (thanks @lollolcheese123) (#3)

### Changed

- Freewheel/Centrifugal Clutch no longer uncouple when the network is overstressed

### Fixed

- Copycat stonecutting recipes not being disabled by feature toggle
- Feature toggles not applied instantly when changed via Create's config GUI
- Brake not emitting smoke if spinning in the opposite direction
- Missing translations in the value panel of Overstress Clutch
- Crash due to concurrent modification when an Overstress Clutch and a Freewheel/Centrifugal Clutch are on the same
  network

## 0.0.5 - 2023-12-22

### Added

- **Feature toggle system** - enable/disable blocks individually via mod common config

### Fixed

- Rendering issues with Fan Splashing Catalyst

### Changed

- Mod icon

## 0.0.4 - 2023-12-20

### Fixed

- Crash due to inject not being remapped (#2)

## 0.0.3 - 2023-12-20

### Added

- New instructions for Sequenced Gearshift
    - Turn until new redstone pulse
    - Turn for time
- New item attributes for attribute filter
    - Max durability
    - Max stack size
- Encased chain cogwheel
- Freewheel Clutch
- Copycat Vertical Step
- Advancement for Shear Pin
- Advancement for Overstress Clutch
- Advancement for Brass Gearbox
- Tooltips for all items (Mostly placeholders for future ponder scenes)

### Changed

- **Target Create version to 0.5.1f**
- The recipe for Empty Fan Catalyst to use less brass
- Advancement ID for Brake
- Texture for Brake
- Texture for overstress clutch
- Centrifugal clutch to update itself when placed
- Item ordering of all shapeless crafting recipes

## 0.0.2 - 2023-12-16

### Added

- 6-way Gearbox
- Brake
- Copycat Block
- Copycat Beam

### Changed

- Copycat Slabs to function like normal slabs while having vertical orientations and placement helpers

## 0.0.1 - 2023-12-8

### Added

- Item attribute - "Contains ID"
- Brass Gearbox
- Centrifugal Clutch
- Copycat Slab
- Inverted Clutch
- Inverted Gearshift
- Overstress Clutch
- Parallel Gearbox
- Shear Pin
- Fan Catalysts (Empty and 4 other variants)