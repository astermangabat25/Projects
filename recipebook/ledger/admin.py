from django.contrib import admin

from .models import Recipe, RecipeIngredient, RecipeImage
# Register your models here.


class RecipeIngredientInline(admin.TabularInline):
    model = RecipeIngredient


class RecipeImageInline(admin.TabularInline):
    model = RecipeImage


class RecipeAdmin(admin.ModelAdmin):
    model = Recipe
    inlines = [RecipeIngredientInline, RecipeImageInline]


admin.site.register(Recipe, RecipeAdmin)
