# Generated by Django 5.0.3 on 2024-05-05 06:33

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('commissions', '0005_alter_job_options_alter_jobapplication_options_and_more'),
    ]

    operations = [
        migrations.AlterField(
            model_name='job',
            name='job_status',
            field=models.CharField(choices=[('OPEN', 'OPEN'), ('FULL', 'FULL')], default='open', max_length=10),
        ),
        migrations.AlterField(
            model_name='jobapplication',
            name='application_status',
            field=models.CharField(choices=[('PENDING', 'PENDING'), ('ACCEPTED', 'ACCEPTED'), ('REJECTED', 'REJECTED')], default='PENDING', max_length=10),
        ),
    ]
