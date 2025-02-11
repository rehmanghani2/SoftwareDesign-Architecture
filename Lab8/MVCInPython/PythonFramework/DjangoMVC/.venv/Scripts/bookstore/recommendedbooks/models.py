from django.db import models

# Create your models here.

class RecommendedBook(models.Model):
    title = models.CharField(max_length=255)
    author = models.CharField(max_length=255)
    description = models.TextField()
    price = models.DecimalField(max_digits=10, decimal_places=2)
    published_date = models.DateField()

    def __str__(self):
        return self.title