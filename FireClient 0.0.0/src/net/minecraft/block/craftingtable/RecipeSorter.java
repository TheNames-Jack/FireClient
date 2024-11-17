package net.minecraft.block.craftingtable;

import java.util.Comparator;

public class RecipeSorter implements Comparator {
	final CraftingManager craftingManager; /* synthetic field */

	public RecipeSorter(CraftingManager craftingmanager) {
		craftingManager = craftingmanager;
		// super();
	}

	public int compareRecipes(IRecipe irecipe, IRecipe irecipe1) {
		if((irecipe instanceof ShapelessRecipes) && (irecipe1 instanceof ShapedRecipes)) {
			return 1;
		}
		if((irecipe1 instanceof ShapelessRecipes) && (irecipe instanceof ShapedRecipes)) {
			return -1;
		}
		if(irecipe1.getRecipeSize() < irecipe.getRecipeSize()) {
			return -1;
		}
		return irecipe1.getRecipeSize() <= irecipe.getRecipeSize() ? 0 : 1;
	}

	public int compare(Object obj, Object obj1) {
		return compareRecipes((IRecipe) obj, (IRecipe) obj1);
	}
}
