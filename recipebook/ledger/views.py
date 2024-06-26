from django.urls import reverse_lazy
from django.shortcuts import render
from django.views.generic.list import ListView
from django.views.generic.detail import DetailView
from django.contrib.auth.mixins import LoginRequiredMixin
from django.views.generic.edit import CreateView
from django.urls import reverse
# Create your views here.

from .models import Recipe, RecipeIngredient, RecipeImage
from .forms import RecipeForm, ImageForm


def recipe_list(request):
    recipes = Recipe.objects.all()
    form = RecipeForm()
    if request.method == "POST":
        form = RecipeForm(request.POST)
        if form.is_valid:
            recipe = Recipe()
            recipe.name = recipe.cleaned_data.get('name')
            recipe.author = recipe.cleaned_data.get('author')
            recipe.save()

            form.save()
    ctx = {
        "recipes": recipes,
        "recipeingredients": RecipeIngredient.objects.all(),

    }
    return render(request, 'recipe_list.html', ctx)


def page(request, pk):
    recipe = Recipe.objects.get(pk=pk)
    ctx = {"recipe": recipe}
    return render(request, 'page.html', ctx)


class RecipeListView(ListView):
    model = Recipe
    template_name = 'recipe_list.html'


class RecipeDetailView(LoginRequiredMixin, DetailView):
    model = Recipe
    template_name = 'page.html'


class RecipeCreateView(LoginRequiredMixin, CreateView):
    model = Recipe
    template_name = 'recipe_create.html'
    form_class = RecipeForm


class RecipeAddImageView(LoginRequiredMixin, CreateView):
    model = RecipeImage
    template_name = 'add_image.html'
    form_class = ImageForm

    def get_success_url(self):
        return reverse_lazy('ledger:recipe', kwargs={'pk': self.object.recipe.pk})

    def get_context_data(self, **kwargs):
        ctx = super().get_context_data(**kwargs)
        ctx['recipe_pk'] = self.kwargs['pk']
        return ctx

    def form_valid(self, form):
        form.instance.recipe = Recipe.objects.get(pk=self.kwargs["pk"])
        return super().form_valid(form)
