from django.shortcuts import render

# Create your views here.

from .models import Book

def book_list(request):
    books = Book.objects.all()
    return render(request, 'recommendedbooks/books.html', {'recommendedbooks': books})
