from django.urls import path
from .views import RecipeListView, RecipeDetailView, RecipeCreateView, recipe_list, RecipeAddImageView

urlpatterns = [
    path('recipes/list', recipe_list, name="list"),
    path('recipe/<int:pk>', RecipeDetailView.as_view(), name="recipe"),
    path('recipe/add', RecipeCreateView.as_view(), name="create"),
    path('recipe/<int:pk>/add_image',
         RecipeAddImageView.as_view(), name="add_image")
]

app_name = 'ledger'
