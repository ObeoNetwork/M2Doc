/*******************************************************************************
 *  Copyright (c) 2023 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.element;

import java.awt.Color;

import org.apache.poi.xwpf.usermodel.Borders;

/**
 * Border definition.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface MBorder {

    /**
     * The type of the border.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    enum Type {
        // @formatter:off
        // CHECKSTYLE:OFF constants
        APPLES(Borders.APPLES),
        ARCHED_SCALLOPS(Borders.ARCHED_SCALLOPS),
        BABY_PACIFIER(Borders.BABY_PACIFIER),
        BABY_RATTLE(Borders.BABY_RATTLE),
        BALLOONS_3_COLORS(Borders.BALLOONS_3_COLORS),
        BALLOONS_HOT_AIR(Borders.BALLOONS_HOT_AIR),
        BASIC_BLACK_DASHES(Borders.BASIC_BLACK_DASHES),
        BASIC_BLACK_DOTS(Borders.BASIC_BLACK_DOTS),
        BASIC_BLACK_SQUARES(Borders.BASIC_BLACK_SQUARES),
        BASIC_THIN_LINES(Borders.BASIC_THIN_LINES),
        BASIC_WHITE_DASHES(Borders.BASIC_WHITE_DASHES),
        BASIC_WHITE_DOTS(Borders.BASIC_WHITE_DOTS),
        BASIC_WHITE_SQUARES(Borders.BASIC_WHITE_SQUARES),
        BASIC_WIDE_INLINE(Borders.BASIC_WIDE_INLINE),
        BASIC_WIDE_MIDLINE(Borders.BASIC_WIDE_MIDLINE),
        BASIC_WIDE_OUTLINE(Borders.BASIC_WIDE_OUTLINE),
        BATS(Borders.BATS),
        BIRDS(Borders.BIRDS),
        BIRDS_FLIGHT(Borders.BIRDS_FLIGHT),
        CABINS(Borders.CABINS),
        CAKE_SLICE(Borders.CAKE_SLICE),
        CANDY_CORN(Borders.CANDY_CORN),
        CELTIC_KNOTWORK(Borders.CELTIC_KNOTWORK),
        CERTIFICATE_BANNER(Borders.CERTIFICATE_BANNER),
        CHAIN_LINK(Borders.CHAIN_LINK),
        CHAMPAGNE_BOTTLE(Borders.CHAMPAGNE_BOTTLE),
        CHECKED_BAR_BLACK(Borders.CHECKED_BAR_BLACK),
        CHECKED_BAR_COLOR(Borders.CHECKED_BAR_COLOR),
        CHECKERED(Borders.CHECKERED),
        CHRISTMAS_TREE(Borders.CHRISTMAS_TREE),
        CIRCLES_LINES(Borders.CIRCLES_LINES),
        CIRCLES_RECTANGLES(Borders.CIRCLES_RECTANGLES),
        CLASSICAL_WAVE(Borders.CLASSICAL_WAVE),
        CLOCKS(Borders.CLOCKS),
        COMPASS(Borders.COMPASS),
        CONFETTI(Borders.CONFETTI),
        CONFETTI_GRAYS(Borders.CONFETTI_GRAYS),
        CONFETTI_OUTLINE(Borders.CONFETTI_OUTLINE),
        CONFETTI_STREAMERS(Borders.CONFETTI_STREAMERS),
        CONFETTI_WHITE(Borders.CONFETTI_WHITE),
        CORNER_TRIANGLES(Borders.CORNER_TRIANGLES),
        COUPON_CUTOUT_DASHES(Borders.COUPON_CUTOUT_DASHES),
        COUPON_CUTOUT_DOTS(Borders.COUPON_CUTOUT_DOTS),
        CRAZY_MAZE(Borders.CRAZY_MAZE),
        CREATURES_BUTTERFLY(Borders.CREATURES_BUTTERFLY),
        CREATURES_FISH(Borders.CREATURES_FISH),
        CREATURES_INSECTS(Borders.CREATURES_INSECTS),
        CREATURES_LADY_BUG(Borders.CREATURES_LADY_BUG),
        CROSS_STITCH(Borders.CROSS_STITCH),
        CUP(Borders.CUP),
        DASH_DOT_STROKED(Borders.DASH_DOT_STROKED),
        DASH_SMALL_GAP(Borders.DASH_SMALL_GAP),
        DASHED(Borders.DASHED),
        DECO_ARCH(Borders.DECO_ARCH),
        DECO_ARCH_COLOR(Borders.DECO_ARCH_COLOR),
        DECO_BLOCKS(Borders.DECO_BLOCKS),
        DIAMONDS_GRAY(Borders.DIAMONDS_GRAY),
        DOT_DASH(Borders.DOT_DASH),
        DOT_DOT_DASH(Borders.DOT_DOT_DASH),
        DOTTED(Borders.DOTTED),
        DOUBLE(Borders.DOUBLE),
        DOUBLE_D(Borders.DOUBLE_D),
        DOUBLE_DIAMONDS(Borders.DOUBLE_DIAMONDS),
        DOUBLE_WAVE(Borders.DOUBLE_WAVE),
        EARTH_1(Borders.EARTH_1),
        EARTH_2(Borders.EARTH_2),
        ECLIPSING_SQUARES_1(Borders.ECLIPSING_SQUARES_1),
        ECLIPSING_SQUARES_2(Borders.ECLIPSING_SQUARES_2),
        EGGS_BLACK(Borders.EGGS_BLACK),
        FANS(Borders.FANS),
        FILM(Borders.FILM),
        FIRECRACKERS(Borders.FIRECRACKERS),
        FLOWERS_BLOCK_PRINT(Borders.FLOWERS_BLOCK_PRINT),
        FLOWERS_DAISIES(Borders.FLOWERS_DAISIES),
        FLOWERS_MODERN_1(Borders.FLOWERS_MODERN_1),
        FLOWERS_MODERN_2(Borders.FLOWERS_MODERN_2),
        FLOWERS_PANSY(Borders.FLOWERS_PANSY),
        FLOWERS_RED_ROSE(Borders.FLOWERS_RED_ROSE),
        FLOWERS_ROSES(Borders.FLOWERS_ROSES),
        FLOWERS_TEACUP(Borders.FLOWERS_TEACUP),
        FLOWERS_TINY(Borders.FLOWERS_TINY),
        GEMS(Borders.GEMS),
        GINGERBREAD_MAN(Borders.GINGERBREAD_MAN),
        GRADIENT(Borders.GRADIENT),
        HANDMADE_1(Borders.HANDMADE_1),
        HANDMADE_2(Borders.HANDMADE_2),
        HEART_BALLOON(Borders.HEART_BALLOON),
        HEART_GRAY(Borders.HEART_GRAY),
        HEARTS(Borders.HEARTS),
        HEEBIE_JEEBIES(Borders.HEEBIE_JEEBIES),
        HOLLY(Borders.HOLLY),
        HOUSE_FUNKY(Borders.HOUSE_FUNKY),
        HYPNOTIC(Borders.HYPNOTIC),
        ICE_CREAM_CONES(Borders.ICE_CREAM_CONES),
        INSET(Borders.INSET),
        LIGHT_BULB(Borders.LIGHT_BULB),
        LIGHTNING_1(Borders.LIGHTNING_1),
        LIGHTNING_2(Borders.LIGHTNING_2),
        MAP_PINS(Borders.MAP_PINS),
        MAPLE_LEAF(Borders.MAPLE_LEAF),
        MAPLE_MUFFINS(Borders.MAPLE_MUFFINS),
        MARQUEE(Borders.MARQUEE),
        MARQUEE_TOOTHED(Borders.MARQUEE_TOOTHED),
        MOONS(Borders.MOONS),
        MOSAIC(Borders.MOSAIC),
        MUSIC_NOTES(Borders.MUSIC_NOTES),
        NIL(Borders.NIL),
        NONE(Borders.NONE),
        NORTHWEST(Borders.NORTHWEST),
        OUTSET(Borders.OUTSET),
        OVALS(Borders.OVALS),
        PACKAGES(Borders.PACKAGES),
        PALMS_BLACK(Borders.PALMS_BLACK),
        PALMS_COLOR(Borders.PALMS_COLOR),
        PAPER_CLIPS(Borders.PAPER_CLIPS),
        PAPYRUS(Borders.PAPYRUS),
        PARTY_FAVOR(Borders.PARTY_FAVOR),
        PARTY_GLASS(Borders.PARTY_GLASS),
        PENCILS(Borders.PENCILS),
        PEOPLE(Borders.PEOPLE),
        PEOPLE_HATS(Borders.PEOPLE_HATS),
        PEOPLE_WAVING(Borders.PEOPLE_WAVING),
        POINSETTIAS(Borders.POINSETTIAS),
        POSTAGE_STAMP(Borders.POSTAGE_STAMP),
        PUMPKIN_1(Borders.PUMPKIN_1),
        PUSH_PIN_NOTE_1(Borders.PUSH_PIN_NOTE_1),
        PUSH_PIN_NOTE_2(Borders.PUSH_PIN_NOTE_2),
        PYRAMIDS(Borders.PYRAMIDS),
        PYRAMIDS_ABOVE(Borders.PYRAMIDS_ABOVE),
        QUADRANTS(Borders.QUADRANTS),
        RINGS(Borders.RINGS),
        SAFARI(Borders.SAFARI),
        SAWTOOTH(Borders.SAWTOOTH),
        SAWTOOTH_GRAY(Borders.SAWTOOTH_GRAY),
        SCARED_CAT(Borders.SCARED_CAT),
        SEATTLE(Borders.SEATTLE),
        SHADOWED_SQUARES(Borders.SHADOWED_SQUARES),
        SHARKS_TEETH(Borders.SHARKS_TEETH),
        SHOREBIRD_TRACKS(Borders.SHOREBIRD_TRACKS),
        SINGLE(Borders.SINGLE),
        SKYROCKET(Borders.SKYROCKET),
        SNOWFLAKE_FANCY(Borders.SNOWFLAKE_FANCY),
        SNOWFLAKES(Borders.SNOWFLAKES),
        SOMBRERO(Borders.SOMBRERO),
        SOUTHWEST(Borders.SOUTHWEST),
        STARS(Borders.STARS),
        STARS_3_D(Borders.STARS_3_D),
        STARS_BLACK(Borders.STARS_BLACK),
        STARS_SHADOWED(Borders.STARS_SHADOWED),
        STARS_TOP(Borders.STARS_TOP),
        SUN(Borders.SUN),
        SWIRLIGIG(Borders.SWIRLIGIG),
        THICK(Borders.THICK),
        THICK_THIN_LARGE_GAP(Borders.THICK_THIN_LARGE_GAP),
        THICK_THIN_MEDIUM_GAP(Borders.THICK_THIN_MEDIUM_GAP),
        THICK_THIN_SMALL_GAP(Borders.THICK_THIN_SMALL_GAP),
        THIN_THICK_LARGE_GAP(Borders.THIN_THICK_LARGE_GAP),
        THIN_THICK_MEDIUM_GAP(Borders.THIN_THICK_MEDIUM_GAP),
        THIN_THICK_SMALL_GAP(Borders.THIN_THICK_SMALL_GAP),
        THIN_THICK_THIN_LARGE_GAP(Borders.THIN_THICK_THIN_LARGE_GAP),
        THIN_THICK_THIN_MEDIUM_GAP(Borders.THIN_THICK_THIN_MEDIUM_GAP),
        THIN_THICK_THIN_SMALL_GAP(Borders.THIN_THICK_THIN_SMALL_GAP),
        THREE_D_EMBOSS(Borders.THREE_D_EMBOSS),
        THREE_D_ENGRAVE(Borders.THREE_D_ENGRAVE),
        TORN_PAPER(Borders.TORN_PAPER),
        TORN_PAPER_BLACK(Borders.TORN_PAPER_BLACK),
        TREES(Borders.TREES),
        TRIANGLE_PARTY(Borders.TRIANGLE_PARTY),
        TRIANGLES(Borders.TRIANGLES),
        TRIBAL_1(Borders.TRIBAL_1),
        TRIBAL_2(Borders.TRIBAL_2),
        TRIBAL_3(Borders.TRIBAL_3),
        TRIBAL_4(Borders.TRIBAL_4),
        TRIBAL_5(Borders.TRIBAL_5),
        TRIBAL_6(Borders.TRIBAL_6),
        TRIPLE(Borders.TRIPLE),
        TWISTED_LINES_1(Borders.TWISTED_LINES_1),
        TWISTED_LINES_2(Borders.TWISTED_LINES_2),
        VINE(Borders.VINE),
        WAVE(Borders.WAVE),
        WAVELINE(Borders.WAVELINE),
        WEAVING_ANGLES(Borders.WEAVING_ANGLES),
        WEAVING_BRAID(Borders.WEAVING_BRAID),
        WEAVING_RIBBON(Borders.WEAVING_RIBBON),
        WEAVING_STRIPS(Borders.WEAVING_STRIPS),
        WHITE_FLOWERS(Borders.WHITE_FLOWERS),
        WOODWORK(Borders.WOODWORK),
        X_ILLUSIONS(Borders.X_ILLUSIONS),
        ZANY_TRIANGLES(Borders.ZANY_TRIANGLES),
        ZIG_ZAG(Borders.ZIG_ZAG),
        ZIG_ZAG_STITCH(Borders.ZIG_ZAG_STITCH);
        // CHECKSTYLE:ON
        // @formatter:on

        /**
         * The {@link Borders}.
         */
        private final Borders type;

        /**
         * Constructor.
         * 
         * @param type
         *            the {@link Borders} type
         */
        Type(Borders type) {
            this.type = type;
        }

        /**
         * Gets the {@link Borders} type.
         * 
         * @return the {@link Borders} type
         */
        public Borders toPOI() {
            return type;
        }
    }

    /**
     * Gets the {@link Type} of this border.
     * 
     * @return the {@link Type} of this border
     */
    Type getType();

    /**
     * Sets the {@link Type}.
     * 
     * @param type
     *            the {@link Type}
     */
    void setType(Type type);

    /**
     * Gets the {@link Color} of this border.
     * 
     * @return the {@link Color} of this border
     */
    Color getColor();

    /**
     * Sets the {@link Color}.
     * 
     * @param color
     *            the new {@link Color}
     */
    void setColor(Color color);

    /**
     * Gets the size of this border in pixels.
     * 
     * @return the size of this border in pixels
     */
    int getSize();

    /**
     * Sets the size.
     * 
     * @param size
     *            the new size
     */
    void setSize(int size);

}
